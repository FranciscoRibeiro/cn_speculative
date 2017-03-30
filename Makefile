build: src/Run.java
	javac src/Run.java -cp "/usr/local/Cellar/swi-prolog/7.4.1/libexec/lib/swipl-7.4.1/lib/jpl.jar" -d .

run:
	java -cp "/usr/local/Cellar/swi-prolog/7.4.1/libexec/lib/swipl-7.4.1/lib/jpl.jar:." -Djava.library.path="/usr/local/Cellar/swi-prolog/7.4.1/libexec/lib/swipl-7.4.1/lib/x86_64-darwin15.6.0" Run
