//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class ComparisonOp extends BinaryOp
{
    // constructors
    public 
    ComparisonOp(strName)
    {
        super(strName);
    }

    // methods
    public STO
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("ComparisonOp.checkOperands()"));
    }
}
