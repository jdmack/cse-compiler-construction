//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class DecOp extends UnaryOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    DecOp(strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO
    checkOperand(STO operand)
    {
        return (new ErrorSTO("DecOp.checkOperands()"));
    }
}
