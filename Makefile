run: compile
	java -jar ../junit5.jar --class-path=. --select-class=FrontendDeveloperTesters
compile:
    javac -cp ../junit5.jar:. *.java
clean:
    rm *.class

