//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class LessThanOp extends ComparisonOp
{
    // constructors
    public 
    LessThanOp(strName)
    {
        super(strName);
    }

    // methods
    public STO
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("LessThanOp.checkOperands()"));
    }
}
