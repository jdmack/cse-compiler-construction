//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class AndBwOp extends BitwiseOp
{
    // constructors
    public 
    AndBwOp(strName)
    {
        super(strName);
    }

    // methods
    public STO
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("AndBwOp.checkOperands()"));
    }
}
