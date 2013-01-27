//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class XorOp extends BitwiseOp
{
    // constructors
    public 
    XorOp(strName)
    {
        super(strName);
    }

    // methods
    public STO
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("XorOp.checkOperands()"));
    }
}
