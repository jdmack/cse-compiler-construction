//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class BooleanOp extends BinaryOp
{
    // constructors
    public 
    BooleanOp(strName)
    {
        super(strName);
    }

    // methods
    public STO
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("BooleanOp.checkOperands()"));
    }
}
