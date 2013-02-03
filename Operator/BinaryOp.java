//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class BinaryOp extends Operator
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    BinaryOp(String strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO
    checkOperands(STO operand1, STO operand2)
    {
        return(new ErrorSTO("BinaryOp.checkOperands()"));
    }

    public STO
    doOperation(ConstSTO operand1, ConstSTO operand2, Type resultType)
    {
        return new ErrorSTO("BinaryOp.doOperation Error");
    }

}
