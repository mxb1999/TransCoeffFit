pName="main.Main"
jfiles=("fit" "main" "uihandler" "plot")
printf "Compiling Java Project\n"
javac --module-path $PATH_TO_FX --add-modules javafx.controls src/*/*.java -Xdiags:verbose -Xlint -d  classes

cd classes
java --module-path $PATH_TO_FX --add-modules javafx.controls $pName