//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class BitwiseOp extends BinaryOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    BitwiseOp(strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("BitwiseOp.checkOperands()"));
    }
}
