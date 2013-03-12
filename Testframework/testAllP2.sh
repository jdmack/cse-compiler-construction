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
compile="../RC"
assemble="make compile"
program="../a.out"
d=`pwd`
tname=$d/testAll.sh

# Make the program
cd ..
make -s debug
cd $d

# Select the diff tool to use
if [[ -x /usr/bin/colordiff ]]; then
    differ=colordiff
else
    differ=diff
fi

# Get lists of tests based on script args
if [[ -n $1 ]]; then
    tests=$(find project2/$1 -name "*${2}*.rc" | sort -n)
else
    tests=$(find project2/* -mindepth 1 -name "*${2}*.rc" | sort -n)
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
    comp_err=$($compile $f 3>&1 2>&3)

    if [[ "$comp_err" != "Compile: success."]] then
        msg=$fail
        echo "Compile: failure."
        cd $d
    else
        ass_err=$($assemble 3>&1 2>&3)

        if [[ "$ass_err" == *Error* ]] then
            msg=$fail
            echo "Assemble: failure."
            cd $d
        else
            err=$($program $f 3>&1 1>$my 2>&3)
            # redirect execution 
            # send stderr to stdout, then send stdout to $my

            # Convert text to unix format
            dos2unix $my &> /dev/null
            dos2unix $ans&> /dev/null

            # Remove line numbers
            sed -e "/Error,* line /d" $my > `dirname $f`/mytemp
            sed -e "/Error,* line /d" $ans > `dirname $f`/myans

            mytemp="`dirname $f`/mytemp"
            myans="`dirname $f`/myans"
            if [[ -e $ans ]]; then
                diff=$($differ -uw $myans $mytemp)
                if [[ -z $diff ]]; then
                    msg=$pass
                    let pass_count=pass_count+1
                  else
                      msg=$fail
                  fi
            else
                  mv $my $ans
                  diff=$(<$ans)
                  msg=$new
            fi
        fi
    fi
    echo -en $msg
    echo " $f"
    if [[ -n $err ]]; then echo "$err"; fi
    if [[ -n $diff ]]; then echo "$diff"; fi
    rm $my
    rm $mytemp
    rm $myans
done
echo -e "\nPass: $pass_count / $total_count\n"
