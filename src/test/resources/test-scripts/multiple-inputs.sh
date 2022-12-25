#!/bin/sh
echo "give the first number"
read number1
echo "give the second number"
read number2

if [ $number1 -gt $number2 ]
then
  echo "$number1 is bigger"
elif [ $number1 -lt $number2 ]
then
  echo "$number2 is bigger"
else
  echo "they are the same"
fi
