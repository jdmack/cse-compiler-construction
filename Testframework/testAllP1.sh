#!/bin/bash

red="\033[1;31m"
green="\033[1;32m"
blue="\033[1;34m"
clear="\033[0m"

new="[ ${blue}NEW ${clear} ]"
pass="[ ${green}PASS${clear} ]"
fail="[ ${red}FAIL${clear} ]"

rcdbg="RCdbg.sh"
rc="RC.sh"
program="./RCdbg.sh"
d=`pwd`
tname=$d/testAllP1.sh

if [ -f $tname ]; then
    echo "Replacing ${d}/${rcdbg}"
    cp $rcdbg ../$rcdbg
    cd ..
    make debug
    cd $d
    cp ../bin/*.class .
else 
    echo "Cannot find ${tname}"
    echo "Make sure you are inside the test framework directory."
    echo "Tests did not run."
    exit
fi


if [[ -x /software/common/gnu/bin/gdiff ]]; then
    differ=/software/common/gnu/bin/gdiff
else
    differ=diff
fi

if [[ -n $1 ]]; then
    tests=$(find project1/$1 -name "*${2}*.rc")
else
    tests=$(find project1/* -mindepth 1 -name "*${2}*.rc")
fi

for f in $tests; do
    my="`dirname $f`/`basename $f .rc`.my.out"
    ans="`dirname $f`/`basename $f .rc`.ans.out"
    err=$($program $f 3>&1 1>$my 2>&3)
    dos2unix $my &> /dev/null
    dos2unix $ans&> /dev/null
    sed -e "/Error.* line /d" $my > `dirname $f`/mytemp
    sed -e "/Error.* line /d" $ans > `dirname $f`/myans
    mytemp="`dirname $f`/mytemp"
    myans="`dirname $f`/myans"
    if [[ -e $ans ]]; then
        diff=$($differ -u $myans $mytemp)
        if [[ -z $diff ]]; then
            msg=$pass
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
rm $mytemp
rm $myans

rm *.class
