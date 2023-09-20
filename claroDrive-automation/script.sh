#!/bin/bash
arguments=$#
tag=$1
browser=$2
repeat=$3

function validate_params() {
    #if [ -z "$repeat" ]; then echo "NULL"; else echo "Not NULL"; fi

    #if [[ "$1" == '' && "$2" == '' ]]; then
    #  echo "error"
    #  exit
    #fi

    if [ $arguments -ne 3 ]; then
    echo the script has at least 3 arguments, the following were expected ./script.sh @tag @browser @repeat
    exit 1
    fi

    #if [ $arguments -lt 2 ]; then
    #  echo The script received less than 2 parameters
    #  exit 2
    #fi

    #if [ $arguments -gt 0 ] && [ -z $name ]; then
    #  echo The script received parameters, but the first one is blank
    #  exit 3
    #fi

}

validate_params

function command() {
       mvn test -Dcucumber.filter.tags=$tag -Dbrowser=$browser

}
function main() {
      mvn clean
      paral=""
      for(( i=0; i<$repeat; i++))
      do
        paral="command&$paral"
      done
      eval $paral
}
main

