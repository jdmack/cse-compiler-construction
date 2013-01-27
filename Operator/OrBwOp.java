//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class OrBwOp extends BitwiseOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    OrBwOp(strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("OrBwOp.checkOperands()"));
    }
}