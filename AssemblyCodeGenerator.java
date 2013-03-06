
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class AssemblyCodeGenerator {

    // Compiler identifier
    private final String COMPILER_IDENT = "WRC 1.0";
    // level of indentation for current code
    private int indent_level = 0;
    
    // Error Messages
    private static final String ERROR_IO_CLOSE     = "Unable to close fileWriter";
    private static final String ERROR_IO_CONSTRUCT = "Unable to construct FileWriter for file %s";
    private static final String ERROR_IO_WRITE     = "Unable to write to fileWriter";

    // FileWriter
    private FileWriter fileWriter;
    
    // Output file header
    private static final String FILE_HEADER = 
        "/*\n" +
        " * Generated %s\n" + 
        " */\n\n";
        
    //-------------------------------------------------------------------------
    //      Constructors
    //-------------------------------------------------------------------------

    public AssemblyCodeGenerator(String fileToWrite) {
        try {
            fileWriter = new FileWriter(fileToWrite);
            
            // write fileHeader with date/time stamp
            writeAssembly(FILE_HEADER, (new Date()).toString());
        } 
        catch (IOException e) {
            System.err.printf(ERROR_IO_CONSTRUCT, fileToWrite);
            e.printStackTrace();
            System.exit(1);
        }
    }

    //-------------------------------------------------------------------------
    //      decreaseIndent
    //-------------------------------------------------------------------------
    public void decreaseIndent() {
        indent_level--;
    }
    
    //-------------------------------------------------------------------------
    //      increaseIndent
    //-------------------------------------------------------------------------
    public void increaseIndent() {
        indent_level++;
    }

    //-------------------------------------------------------------------------
    //      dispose
    //-------------------------------------------------------------------------
    public void dispose() {
        // Close the filewriter
        try {
            fileWriter.close();
        } 
        catch (IOException e) {
            System.err.println(ERROR_IO_CLOSE);
            e.printStackTrace();
            System.exit(1);
        }
    }

    //-------------------------------------------------------------------------
    //      writeAssembly
    //-------------------------------------------------------------------------
    // params = String []
    public void writeAssembly(String template, String ... params) {
        StringBuilder asStmt = new StringBuilder();
        
        // Indent current line
        for (int i=0; i < indent_level; i++) {
            asStmt.append(SparcInstr.INDENTOR);
        }
        
        asStmt.append(String.format(template, (Object[])params));
        
        try {
            fileWriter.write(asStmt.toString());
        } 
        catch (IOException e) {
            System.err.println(ERROR_IO_WRITE);
            e.printStackTrace();
        }
    }
    
    //-------------------------------------------------------------------------
    //      example
    //-------------------------------------------------------------------------
    public void example() {
        AssemblyCodeGenerator myAsWriter = new AssemblyCodeGenerator("output.s");

        myAsWriter.increaseIndent();
        myAsWriter.writeAssembly(SparcInstr.TWO_PARAM, SparcInstr.SET_OP, String.valueOf(4095), "%l0");
        myAsWriter.increaseIndent();
        myAsWriter.writeAssembly(SparcInstr.TWO_PARAM, SparcInstr.SET_OP, String.valueOf(1024), "%l1");
        myAsWriter.decreaseIndent();
        
        myAsWriter.writeAssembly(SparcInstr.TWO_PARAM, SparcInstr.SET_OP, String.valueOf(512), "%l2");
        
        myAsWriter.decreaseIndent();
        myAsWriter.dispose();
    }

    //-------------------------------------------------------------------------
    //      String Utility Functions
    //-------------------------------------------------------------------------
    public String quoted(String str)
    {
        return "\"" + str + "\"";
    }

    public String sqBracketed(String str)
    {
        return "[" + str + "]";
    }

    //-------------------------------------------------------------------------
    //
    //      Code Generation Functions
    //  
    //-------------------------------------------------------------------------

    //-------------------------------------------------------------------------
    //      WriteComment
    //-------------------------------------------------------------------------
    public void writeComment(String comment)
    {
        writeAssembly(SparcInstr.LINE, SparcInstr.COMMENT + " " + comment);
    }

    public void writeCommentHeader(String comment)
    {
        writeAssembly(SparcInstr.BLANK_LINE);
        writeAssembly(SparcInstr.LINE, SparcInstr.COMMENT + "----" + comment + "----");
    }

    //-------------------------------------------------------------------------
    //      DoProgramStart
    //-------------------------------------------------------------------------
    public void DoProgramStart(String filename)
    {
        increaseIndent();
    
        writeCommentHeader("Starting Program");

        writeAssembly(SparcInstr.ONE_PARAM, SparcInstr.FILE_DIR, quoted(filename));
        writeAssembly(SparcInstr.ONE_PARAM, SparcInstr.IDENT_DIR, quoted(COMPILER_IDENT));
    }

    //-------------------------------------------------------------------------
    //      DoFuncStart
    //-------------------------------------------------------------------------
    public void DoFuncStart(FuncSTO funcSto)
    {
        writeCommentHeader("Function: " + funcSto.getName());
        writeAssembly(SparcInstr.ONE_PARAM, SparcInstr.SECTION_DIR, SparcInstr.TEXT_SEC);
        writeAssembly(SparcInstr.ONE_PARAM, SparcInstr.ALIGN_DIR, String.valueOf(4));
        writeAssembly(SparcInstr.ONE_PARAM, SparcInstr.GLOBAL_DIR, funcSto.getName());
        writeAssembly(SparcInstr.BLANK_LINE);

        // Write the function label
        decreaseIndent();
        writeAssembly(SparcInstr.LABEL, funcSto.getName());
        increaseIndent();

        // Move the saved offset for this function into %g1 and then execute the save instruction that shifts the stack
        writeAssembly(SparcInstr.TWO_PARAM, SparcInstr.SET_OP, SparcInstr.SAVE_WORD + "." + funcSto.getName(), SparcInstr.REG_GLOBAL1);
        writeAssembly(SparcInstr.THREE_PARAM, SparcInstr.SAVE_OP, SparcInstr.REG_STACK, SparcInstr.REG_GLOBAL1, SparcInstr.REG_STACK);
        writeAssembly(SparcInstr.BLANK_LINE);
    }

    //-------------------------------------------------------------------------
    //      DoFuncFinish
    //-------------------------------------------------------------------------
    public void DoFuncFinish(FuncSTO funcSto)
    {
        // Perform return/restore
        // TODO: Right now, two sets of ret/restore will be printed if the function did explicit "return"
        writeAssembly(SparcInstr.BLANK_LINE);
        writeAssembly(SparcInstr.NO_PARAM, SparcInstr.RET);
        writeAssembly(SparcInstr.NO_PARAM, SparcInstr.RESTORE);
        writeAssembly(SparcInstr.BLANK_LINE);

        // Write the assembler directive to save the amount of bytes needed for the save operation
        decreaseIndent();
        writeAssembly(SparcInstr.SAVE_FUNC, funcSto.getName(), String.valueOf(92), String.valueOf(funcSto.getLocalVarBytes()));
    }
    
    //-------------------------------------------------------------------------
    //      DoReturn
    //-------------------------------------------------------------------------
    public void DoReturn(STO returnSto)
    {
        // Load the return value into the return register
        if(!returnSto.getType().isVoid())
            writeAssembly(SparcInstr.TWO_PARAM, returnSto.load(), SparcInstr.REG_SET_RETURN);

        // Perform return/restore
        writeAssembly(SparcInstr.NO_PARAM, SparcInstr.RET);
        writeAssembly(SparcInstr.NO_PARAM, SparcInstr.RESTORE);
    }


}
