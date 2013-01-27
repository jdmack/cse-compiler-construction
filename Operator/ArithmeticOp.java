//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class ArithmeticOp extends BinaryOp
{
    // constructors
    public 
    ArithmeticOp ()
    {
        super();
    }
    
    // methods
    public STO
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("ArithmeticOp.checkOperands()"));
    }
}
