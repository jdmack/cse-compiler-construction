//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class MulOp extends ArithmeticOp
{
    // constructors
    public 
    MulOp ()
    {
        super();
    }

    // methods
    public STO
    checkOperands (STO op1, STO op2)
    {
        return (new ErrorSTO("MulOp.checkOperands()"));
    }
}
