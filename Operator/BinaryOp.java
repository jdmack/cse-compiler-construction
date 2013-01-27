//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class BinaryOp extends Operator
{
    // constructors
    public 
    BinaryOp(strName)
    {
        super(strName);
    }

    // methods
    public STO
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("BinaryOp.checkOperands()"));
    }
}
