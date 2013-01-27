//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class GreaterThanEqualOp extends ComparisonOp
{
    // constructors
    public 
    GreaterThanEqualOp(strName)
    {
        super(strName);
    }

    // methods
    public STO
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("GreaterThanEqualOp.checkOperands()"));
    }
}
