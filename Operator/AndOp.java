//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class AndOp extends BooleanOp
{
    // constructors
    public 
    AndOp(strName)
    {
        super(strName);
    }

    // methods
    public STO
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("AndOp.checkOperands()"));
    }
}
