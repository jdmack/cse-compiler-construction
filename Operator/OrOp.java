//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class OrOp extends BooleanOp
{
    // constructors
    public 
    OrOp(strName)
    {
        super(strName);
    }

    // methods
    public STO
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("OrOp.checkOperands()"));
    }
}
