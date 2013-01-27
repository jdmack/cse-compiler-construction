//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class LessThanEqualOp extends ComparisonOp
{
    // constructors
    public 
    LessThanEqualOp(strName)
    {
        super(strName);
    }

    // methods
    public STO
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("LessThanEqualOp.checkOperands()"));
    }
}
