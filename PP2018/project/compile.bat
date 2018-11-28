rd /s /q classes
mkdir classes
call scalac -classpath lib/proj-lib.jar:classes/ -d classes/ src/Main.scala
call scalac -classpath lib/proj-lib.jar:classes/ -d classes/ src/Test.scala
