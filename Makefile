JAVAC	:= javac
JAVA	:= java
JUNIT	:= junit5.jar
BDTESTCLASS	:= BackendDeveloperTests
FDTESTCLASS	:= FrontendDeveloperTests
CLASSFILES	:= $(patsubst %.java,%.class,$(wildcard *.java))

default: runBDTests

%.class : %.java
	$(JAVAC) -cp .:$(JUNIT) $<

runBDTests: $(CLASSFILES)
	$(JAVA) -jar $(JUNIT) -cp . --select-class=$(BDTESTCLASS)

runFDTests: $(CLASSFILES)
	$(JAVA) -jar $(JUNIT) -cp . --select-class=$(FDTESTCLASS)

run: $(CLASSFILES)
	$(JAVA) -cp . Main.java

runTests: runBDTests runFDTests

clean:
	rm *.class
	@echo done
