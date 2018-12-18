#!/usr/bin/env bash

rm -rf classes
mkdir classes
scalac -classpath lib/proj-lib.jar:classes/ -d classes/ src/Main.scala
scalac -classpath lib/proj-lib.jar:classes/ -d classes/ src/MyParser.scala
scalac -classpath lib/proj-lib.jar:classes/ -d classes/ src/Test.scala
