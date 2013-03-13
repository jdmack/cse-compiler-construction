#!/bin/bash

# usage:
#   ./testAllP2.sh [phase] [check #]
#


# Define colors
red="\033[1;31m"
green="\033[1;32m"
blue="\033[1;34m"
clear="\033[0m"

# Set state colors
new="[ ${blue}NEW ${clear} ]"
pass="[ ${green}PASS${clear} ]"
fail="[ ${red}FAIL${clear} ]"

# Assign executable vars
rc="RC.sh"
compile="./RC"
assemble="make compile"
program="../a.out"
d=`pwd`
tname=$d/testAll.sh

# Make the program
cd ..
make -s debug
cd $d

# Select the diff tool to use
if [[ -x /usr/bin/colordiff || /home/solaris/ieng9/oce/1e/jdmack/bin/colordiff ]]; then
    differ=colordiff
else
    differ=diff
fi

# Get lists of tests based on script args
if [[ -n $2 ]]; then
    tests=$(find project2/$1 -name "*${2}*.rc" | sort -n)
else
    tests=$(find project2/* -mindepth 1 -name "*${1}*.rc" | sort -n)
fi

pass_count=0
total_count=0

echo -e "\nTests:\n$tests"

echo -e "\n\nBeginning tests...\n"

# Run each test
for f in $tests; do

    let total_count=total_count+1

    # Assign output files and redirect output
    my="`dirname $f`/`basename $f .rc`.my.out"
    ans="`dirname $f`/`basename $f .rc`.ans.out"

    # Run test
    cd ..
    comp_out=$($compile Testframework/$f 3>&1 2>&3)

    if [[ "$comp_out" != *success* ]]; then
        msg=$fail
    #    echo "Compile: failure."
        cd $d
    else
        ass_out=$($assemble 3>&1 2>&3)

        if [[ $ass_out == *Error* ]]; then
            msg=$fail
            echo "Assemble: failure."
            cd $d
        else
            cd $d
            prog_out=$($program $f 3>&1 1>$my 2>&3)
            # redirect execution 
            # send stderr to stdout, then send stdout to $my

            # Convert text to unix format
            dos2unix $my &> /dev/null
            dos2unix $ans&> /dev/null

            # Remove line numbers
            # sed -e "/Error,* line /d" $my > `dirname $f`/mytemp
            # sed -e "/Error,* line /d" $ans > `dirname $f`/myans
            cat $my > `dirname $f`/mytemp
            cat $ans > `dirname $f`/myans 2> /dev/null

            mytemp="`dirname $f`/mytemp"
            myans="`dirname $f`/myans"
            if [[ -e $ans ]]; then
                mydiff="`dirname $f`/mydiff"
                $($differ -uw $myans $mytemp > $mydiff)
                if [[ ! -s $mydiff || "$(head -n 1 $mydiff)" == *No* ]]; then 
                    msg=$pass
                    let pass_count=pass_count+1
                else
                    msg=$fail
                fi
            else
                cp $my $ans
                diff=$(<$ans)
                msg=$new
            fi
        fi
    fi
    echo -en $msg
    echo " $f"
    if [[ -n $prog_out ]]; then echo "$prog_out"; fi
    if [[ -s $mydiff && $mydiff != *No* ]]; then cat "$mydiff"; fi
    if [[ -n $my ]]; then rm $my; fi
    if [[ -n $mydiff ]]; then rm $mydiff; fi
    if [[ -n $mytemp ]]; then rm $mytemp; fi
    if [[ -n $myans ]]; then rm $myans; fi
done
echo -e "\nPass: $pass_count / $total_count\n"
