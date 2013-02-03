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
        Double value = 0.0;

        if(resultType.isInt())
        {
            value = new Double(operand1.getIntValue() + operand2.getIntValue());
        }
        else if(resultType.isFloat())
        {
            value = new Double(operand1.getFloatValue() + operand2.getFloatValue());
        }

        return new ConstSTO("AddOp.doOperation Result", resultType, value);
        return(new ErrorSTO("BinaryOp.doOperation()"));
    }
}
