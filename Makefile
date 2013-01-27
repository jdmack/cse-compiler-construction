
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
	Type/Type.java \
	Scope.java \
	SymbolTable.java \
	MyParser.java \
	RC.java \
	RCdbg.java

new:
	make clean
	make rc

debug:
	make clean
	make rcdbg

rc: $(SOURCES) parser.java sym.java
	javac -sourcepath .:./Type:./STO:./Opeartor -d bin RC.java
	cp RC.sh RC
	chmod 755 RC

warnings: $(SOURCES) parser.java sym.java
	make clean
	javac -Xlint -sourcepath .:./Type:./STO:./Opeartor -d bin RC.java
	cp RC.sh RC
	chmod 755 RC

rcdbg: $(SOURCES) parser.java sym.java
	javac -sourcepath .:./Type:./STO:./Opeartor -d bin RCdbg.java
	cp RCdbg.sh RC
	chmod 755 RC

parser.java: rc.cup
	javacup < rc.cup

clean:
	rm -f *.class bin/*.class RC parser.java sym.java a.out core rc.s

tar:
	tar cvf rc.tar $(SOURCES) Makefile RC.sh

#       Makes a backup called backups/MMDDMM:SS.tar.Z
backup:
	-@make clean
	-@mkdir backups
	tar cvf - $(SOURCES) Makefile RC.sh | compress > backups/`date +%m%d%R`.tar.Z
