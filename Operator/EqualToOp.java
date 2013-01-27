//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class EqualToOp extends ComparisonOp
{
    // constructors
    public 
    EqualToOp(strName)
    {
        super(strName);
    }

    // methods
    public STO
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("EqualToOp.checkOperands()"));
    }
}
