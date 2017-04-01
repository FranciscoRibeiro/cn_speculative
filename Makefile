_CLASSES= Atom.class AskableAtom.class Constraint.class Film.class Process.class SourceDatabase.class
CLASSES = $(patsubst %,src/%,$(_CLASSES))

build: $(CLASSES) src/Run.java
	javac src/Run.java -cp "/usr/local/Cellar/swi-prolog/7.4.1/libexec/lib/swipl-7.4.1/lib/jpl.jar" -d .

src/SourceDatabase.class: src/SourceDatabase.java
	javac src/SourceDatabase.java -cp "src/:/usr/local/JADE-all-4.4.0/jade/lib/jade.jar"

src/%.class: src/%.java
	javac $< -cp src/

run:
	java -cp "/usr/local/Cellar/swi-prolog/7.4.1/libexec/lib/swipl-7.4.1/lib/jpl.jar:." -Djava.library.path="/usr/local/Cellar/swi-prolog/7.4.1/libexec/lib/swipl-7.4.1/lib/x86_64-darwin15.6.0" Run
