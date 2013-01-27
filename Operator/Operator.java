//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

abstract class Operator
{
    // members
    private String m_opName;


    // constructors
    public 
    Operator(String strName)
    {
        setName(strName);
    }

    // methods
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

    public STO 
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("Operator: abstract class")); 
    }
}
