
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class AssemblyCodeGenerator {

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
    
    

    
    // 9) This signature may look foreign to you.  What is says is that we have 
    //    public method named writeAssembly which takes as parameters a String
    //    followed by 1 or more strings.  This construct is called "VarArgs" and
    //    is a Java 1.5 feature.  This allows you to write one method which can
    //    be applied to any number of parameters.  This method simply takes in 
    //    a template and all the strings that will be substituted into the 
    //    template.  When you are actually in the method, the parameter 
    //    String ... params will be an array of strings.
    public void writeAssembly(String template, String ... params) {
        StringBuilder asStmt = new StringBuilder();
        
        // 10) This is where we use our indent_level.  We will indent indent_level levels
        //     of indentation.  That is an awkward sentence isn't it!  StringBuilder is
        //     an efficient class to build strings from concatentations.  If your 
        //     concatenations span multiple lines of code, using a StringBuilder can
        //     offer signifigant performance when compared to using the + operator.
        //     This topic can get fairly detailed, send me an email or come talk to me
        //     in the lab for more details.
        for (int i=0; i < indent_level; i++) {
            asStmt.append(SparcInstr.SEPARATOR);
        }
        
        // 11) Here we are writing the message to file, notice we are using the 
        //     String.format method which takes a printf like format string followed
        //     by an array of Objects which are the parameters to the format string.
        asStmt.append(String.format(template, (Object[])params));
        
        try {
            fileWriter.write(asStmt.toString());
        } 
        catch (IOException e) {
            System.err.println(ERROR_IO_WRITE);
            e.printStackTrace();
        }
    }
    
    // 12) Main is just a small demo that will create a tiny assembly file in the
    //     current directory called "output.s".  This file doesn't compile and is
    //     not meant to.
    public static void main(String args[]) {
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
    //
    //      Code Generation Functions
    //  
    //-------------------------------------------------------------------------

    //-------------------------------------------------------------------------
    //      DoProgramStart
    //-------------------------------------------------------------------------
    public void DoProgramStart(String filename)
    {


    }




}
