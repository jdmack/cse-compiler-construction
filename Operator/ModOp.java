//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class ModOp extends ArithmeticOp
{
    // constructors
    public 
    ModOp ()
    {
        super();
    }

    // methods
    public STO
    checkOperands (STO op1, STO op2)
    {
        return (new ErrorSTO("ModOp.checkOperands()"));
    }
}
