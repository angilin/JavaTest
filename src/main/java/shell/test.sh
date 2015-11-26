#!/bin/bash
#!asdadasda 不影响上面的bash
a=2 b="abc"
echo "A is:" $b $a

echo "this is the ${a}nd"

echo "this is the {$a}nd"
echo "this is the "$a"nd"

var=1
let "var+=1"
echo $var
var="$[$var+1]"
echo $var
 ((var=var+5))
echo $var
var="$(expr "$var" + 1)" #不建议使用
echo $var
var="`expr "$var" + 1`" #强烈不建议使用，注意加号两边的空格，否则还是按照字符串的方式赋值,`为键盘上Esc下方的`，而不是单引号'。
echo $var

var=$(($var+1))
echo $var

var=$((var+1))
echo $var

a=$((a+5))
echo $a

aaaaa="abc"
echo $SHELL

if [ ${SHELL} = "/bin/bash" ]; then  echo "your login shell is the bash (bourne again shell)"
else
   echo "your login shell is not bash but ${SHELL}"
fi
