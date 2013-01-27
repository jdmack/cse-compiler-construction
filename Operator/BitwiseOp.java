//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class BitwiseOp extends BinaryOp
{
    // constructors
    public 
    BitwiseOp(strName)
    {
        super(strName);
    }

    // methods
    public STO
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("BitwiseOp.checkOperands()"));
    }
}
