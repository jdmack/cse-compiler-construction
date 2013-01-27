//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

abstract class Operator
{
    //---------------------------------------------------------------------
    //      Instance Variables
    //---------------------------------------------------------------------
    private String m_opName;


    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    Operator(String strName)
    {
        setName(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public String
    getName()
    {
        return m_opName;
    }

    public void
    setName(String str)
    {
        m_typeName = str;
    }

// Decided this should go in BinaryOp then Unary Op gets checkOperand
/*  public STO 
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("Operator: abstract class")); 
    }
*/
}
