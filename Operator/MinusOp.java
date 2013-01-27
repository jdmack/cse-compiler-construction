//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class MinusOp extends ArithmeticOp
{
    // constructors
    public 
    MinusOp ()
    {
        super();
    }

    // methods
    public STO
    checkOperands (STO op1, STO op2)
    {
        return (new ErrorSTO("MinusOp.checkOperands()"));
    }
}
