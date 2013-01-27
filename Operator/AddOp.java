//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class AddOp extends ArithmeticOp
{
    // constructors
    public 
    AddOp ()
    {
        super();
    }

    // methods
    public STO
    checkOperands (STO op1, STO op2)
    {
        return (new ErrorSTO("AddOp.checkOperands()"));
    }
}
