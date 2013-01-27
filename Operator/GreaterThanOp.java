//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class GreaterThanOp extends ComparisonOp
{
    // constructors
    public 
    GreaterThanOp(strName)
    {
        super(strName);
    }

    // methods
    public STO
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("GreaterThanOp.checkOperands()"));
    }
}
