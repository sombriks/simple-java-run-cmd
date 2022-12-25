#!/bin/sh
echo "guess the secret number"
read number

if [ $number = 7 ]
then
  echo "yes it is 7!"
else
  echo "you didn't guessed it right..."
fi
