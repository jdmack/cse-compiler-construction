//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class OrBwOp extends BitwiseOp
{
    // constructors
    public 
    OrBwOp(strName)
    {
        super(strName);
    }

    // methods
    public STO
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("OrBwOp.checkOperands()"));
    }
}
