
SOURCES = \
	ErrorMsg.java \
	ErrorPrinter.java \
	Formatter.java \
	Token.java \
	LineNumberPushbackStream.java \
	Lexer.java \
	STO/STO.java \
	STO/ExprSTO.java \
	STO/ConstSTO.java \
	STO/VarSTO.java \
	STO/FuncSTO.java \
	STO/TypedefSTO.java \
	STO/ErrorSTO.java \
	STO/TypedefSTO.java \
	Type/Type.java \
	Scope.java \
	SymbolTable.java \
	MyParser.java \
	RC.java \
	RCdbg.java

JAVACOPT=-sourcepath .:./Type:./STO:./Operator -classpath .

new:
	make clean
	make rc

debug:
	make clean
	make rcdbg

rc: $(SOURCES) parser.java sym.java
	javac $(JAVACOPT) -d bin RC.java
	cp RC.sh RC
	chmod 755 RC

rcdbg: $(SOURCES) parser.java sym.java
	javac $(JAVACOPT) -d bin RCdbg.java
	cp RCdbg.sh RC
	chmod 755 RC

warnings: $(SOURCES) parser.java sym.java
	make clean
	make parser.java
	javac -Xlint $(JAVACOPT) -d bin RC.java


parser.java: rc.cup
	./javacup < rc.cup

clean:
	 rm -f *.class bin/*.class RC parser.java sym.java a.out core rc.s

tar:
	tar cvf rc.tar $(SOURCES) Makefile RC.sh

#       Makes a backup called backups/MMDDMM:SS.tar.Z
backup:
	-@make clean
	-@mkdir backups
	tar cvf - $(SOURCES) Makefile RC.sh | compress > backups/`date +%m%d%R`.tar.Z

turnin:
	mv Operator/* .
	mv STO/* .
	mv Type/* .
	rmdir Operator 
	rmdir STO 
	rmdir Type 
	mv .git ../ 
	rm info.txt 
	rm Makefile
	mv Makefile.orig Makefile
	make new 

test:
	cd Testframework; ./testAllP1.sh

CC=cc
compile:
	$(CC) rc.s input.c output.s $(LINKOBJ)

