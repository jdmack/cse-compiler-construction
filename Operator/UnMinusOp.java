//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class UnMinusOp extends UnaryOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    UnMinusOp(String strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO
    checkOperand(STO operand)
    {
        STO resultSTO;

        // Check #1 - UnMinus - operand numeric
        // Check operand
        if(!operand.getType().isNumeric())
        {
            return(new ErrorSTO(Formatter.toString("Incompatible type %T to unary operator %O, numeric expected.", operand.getType().getName(), this.getName())));
        }

        if(operand.isConst())
        {
            resultSTO = new ConstSTO("UnMinus.checkOperand() Result", new FloatType());
        }
        else
        {
            resultSTO = new ExprSTO("UnMinus.checkOperand() Result", new FloatType());
        }

        return resultSTO;
}
    public STO
    doOperation(ConstSTO operand, Type resultType)
    {
        Double value = 0.0;

        if(resultType.isInt())
        {
            value = new Double(-operand.getIntValue());
        }
        else if(resultType.isFloat())
        {
            value = new Double(-operand.getFloatValue());
        }

        return new ConstSTO("UnMinusOp.doOperation Result", resultType, value);
    }
}
