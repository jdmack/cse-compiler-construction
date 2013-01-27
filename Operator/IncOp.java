//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class IncOp extends UnaryOp
{
    // constructors
    public 
    IncOp(strName)
    {
        super(strName);
    }

    // methods
    public STO
    checkOperands(STO op1, STO op2)
    {
        return (new ErrorSTO("IncOp.checkOperands()"));
    }
}
