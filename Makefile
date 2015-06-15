JFLAGS = -d bin -cp src
JC = javac
.SUFFIXES: .java .class
.java.class: 
	$(JC) $(JFLAGS) $*.java

CLASSES = \
    src/DiscreteEventSimulation.java \
    src/EventHandler.java \
    src/EventHeap.java \
    src/Event.java \
    src/Machine.java \
    src/Repairman.java \
    src/Simulation.java \
    src/User.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) bin/*.class