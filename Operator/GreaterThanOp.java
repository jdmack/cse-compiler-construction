//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class GreaterThanOp extends ComparisonOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    GreaterThanOp(strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO
    checkOperands(STO operand1, STO operand2)
    {
        return (new ErrorSTO("GreaterThanOp.checkOperands()"));
    }
}
