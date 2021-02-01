pName="main.Main"
jfiles=("fit" "main" "uihandler")
printf "Compiling Java Project\n"
javac src/*/*.java -Xdiags:verbose -d  classes

cd classes
java $pName