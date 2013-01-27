//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class ComparisonOp extends BinaryOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    ComparisonOp(strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("ComparisonOp.checkOperands()"));
    }
}
