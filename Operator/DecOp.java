//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class DecOp extends UnaryOp
{
    // constructors
    public 
    DecOp(strName)
    {
        super(strName);
    }

    // methods
    public STO
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("DecOp.checkOperands()"));
    }
}
