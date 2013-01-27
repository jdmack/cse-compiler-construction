//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class UnaryOp extends Operator
{
    // constructors
    public 
    UnaryOp(strName)
    {
        super(strName);
    }

    // methods
    public STO
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("UnaryOp.checkOperands()"));
    }
}
