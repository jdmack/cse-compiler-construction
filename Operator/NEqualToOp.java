//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class NEqualToOp extends ComparisonOp
{
    // constructors
    public 
    NEqualToOp(strName)
    {
        super(strName);
    }

    // methods
    public STO
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("NEqualToOp.checkOperands()"));
    }
}
