#!/bin/bash

# usage:
#   ./testAllP1.sh [phase] [check #]
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
program="../RC"
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
    tests=$(find project1/$1 -name "*${2}*.rc")
else
    tests=$(find project1/* -mindepth 1 -name "*${2}*.rc")
fi

pass_count=0
total_count=0

echo -e "\nBeginning tests...\n"


# Run each test
for f in $tests; do
    let total_count=total_count+1
    my="`dirname $f`/`basename $f .rc`.my.out"
    ans="`dirname $f`/`basename $f .rc`.ans.out"
    err=$($program $f 3>&1 1>$my 2>&3)
    dos2unix $my &> /dev/null
    dos2unix $ans&> /dev/null
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
    echo -en $msg
    echo " $f"
    if [[ -n $err ]]; then echo "$err"; fi
    if [[ -n $diff ]]; then echo "$diff"; fi
done
echo -e "\nPass: $pass_count / $total_count\n"
rm $my
rm $mytemp
rm $myans
