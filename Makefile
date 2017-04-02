_CLASSES= Atom.class SpecAgent.class AskableAtom.class Constraint.class Film.class Process.class SourceDatabase.class Run.class
CLASSES = $(patsubst %,src/%,$(_CLASSES))

run: $(CLASSES)
	java -cp "src/:/usr/local/JADE-all-4.4.0/jade/lib/jade.jar" Run

src/%.class: src/%.java
	javac $< -cp "src/:/usr/local/JADE-all-4.4.0/jade/lib/jade.jar"
