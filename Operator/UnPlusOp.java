//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class UnPlusOp extends UnaryOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    UnPlusOp(String strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO
    checkOperand(STO operand)
    {
        return (new ErrorSTO("UnPlusOp.checkOperands()"));
    }
}
