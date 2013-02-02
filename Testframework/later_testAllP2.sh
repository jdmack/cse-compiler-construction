#!/bin/bash

red="\033[1;31m"
green="\033[1;32m"
blue="\033[1;34m"
clear="\033[0m"

new="[ ${blue}NEW${clear} ]"
pass="[ ${green}PASS${clear} ]"
fail="[ ${red}FAIL${clear} ]"
badtest="[ ${red}COMPILEFAIL${clear} ]"
compiled="compileSuccess"

rcdbg="RCdbg.sh"
rc="RC.sh"
program="./RCdbg.sh"
d=`pwd`
tname=$d/testAll.sh

if [ -f $tname ]; then
    echo "Replacing ${d}/${rcdbg}"
    cp $rcdbg ../$rcdbg
    cd ..
    make debug
    cd $d
    cp ../*.class .
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
    tests=$(find project2/$1 -name "*${2}*.rc")
else
    tests=$(find project2/* -mindepth 1 -name "*${2}*.rc")
fi

for f in $tests; do
    fname="`dirname $f`/`basename $f .rc`"
    my="`dirname $f`/`basename $f .rc`.my.out"
    ans="`dirname $f`/`basename $f .rc`.ans.out"
    err=$($program $f 3>&1 1>$my 2>&3)
    diff=$($differ -u $my $compiled)
    if [[ -z $diff ]]; then
        cp rc.s $fname.s
        cc rc.s input.c output.s -o a.out
        ./a.out > $my
        dos2unix $my &> /dev/null
        dos2unix $ans&> /dev/null
        if [[ -e $ans ]]; then
            diff=$($differ -u $ans $my)
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
        rm rc.s
    else
        msg=$badtest
            echo -en $msg
            echo " $f"
        cat $my
        continue
    fi
done
rm *.class
if [[ -e rc.s ]]; then
    rm rc.s
fi
if [[ -e a.out ]]; then
rm a.out
fi
