//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class UnPlusOp extends UnaryOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    UnPlusOp(String strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO
    checkOperand(STO operand)
    {
        return(new ErrorSTO("UnPlusOp.checkOperands()"));
    }
    public STO
    doOperation(ConstSTO operand, Type resultType)
    {
        Double value = 0.0;

        if(resultType.isInt())
        {
            value = new Double(+operand.getIntValue());
        }
        else if(resultType.isFloat())
        {
            value = new Double(+operand.getFloatValue());
        }

        return new ConstSTO("UnPlusOp.doOperation Result", resultType, value);
    }

}
