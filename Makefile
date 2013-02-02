
SOURCES = \
	ErrorMsg.java \
	ErrorPrinter.java \
	Formatter.java \
	Token.java \
	LineNumberPushbackStream.java \
	Lexer.java \
	STO.java \
	ExprSTO.java \
	ConstSTO.java \
	VarSTO.java \
	FuncSTO.java \
	TypedefSTO.java \
	ErrorSTO.java \
	Type.java \
	Scope.java \
	SymbolTable.java \
	MyParser.java \
	RC.java \
	RCdbg.java

new:
	make clean
	cp src/* ./
	make rc
	rm *.java rc.cup

debug:
	make clean
	cp src/* ./
	make rcdbg
	rm *.java rc.cup

rc: $(SOURCES) parser.java sym.java
	javac RC.java
	cp RC.sh RC
	chmod 755 RC

rcdbg: $(SOURCES) parser.java sym.java
	javac RCdbg.java
	cp RCdbg.sh RC
	chmod 755 RC

parser.java: rc.cup
	javacup < rc.cup

clean:
	rm -f rc.cup *.java *.class RC parser.java sym.java a.out core rc.s

tar:
	tar cvf rc.tar $(SOURCES) Makefile RC.sh

#       Makes a backup called backups/MMDDMM:SS.tar.Z
backup:
	-@make clean
	-@mkdir backups
	tar cvf - $(SOURCES) Makefile RC.sh | compress > backups/`date +%m%d%R`.tar.Z
