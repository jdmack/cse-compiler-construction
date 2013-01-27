//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class DivOp extends ArithmeticOp
{
    // constructors
    public 
    DivOp ()
    {
        super();
    }

    // methods
    public STO
    checkOperands (STO op1, STO op2)
    {
        return (new ErrorSTO("DivOp.checkOperands()"));
    }
}
