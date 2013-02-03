//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class IncOp extends UnaryOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    IncOp(String strName)
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

        // Check #2 - increment, decrement - operand numeric
        if((!operand.getType().isNumeric()) && (!operand.getType().isPointer()))
        {
            return(new ErrorSTO(Formatter.toString(ErrorMsg.error2_Type, operand.getType().getName(), this.getName())));
        }

        // Check #2 - increment,  decrement - operand not modifiable L-value
        if(!operand.isModLValue())
        {
            return(new ErrorSTO(Formatter.toString(ErrorMsg.error2_Lval, this.getName())));
        }

        // Passed checks, determine result type
        if(operand.getType().isInt())
        {
            resultSTO = new ExprSTO("IncOp.checkOperand() Result", new IntType());
        }
        else if(operand.getType().isFloat())
        {
            resultSTO = new ExprSTO("IncOp.checkOperand() Result", new FloatType());
        }
        else
        {
            return(new ErrorSTO("This will never happen, making compiler happy"));
        }

        return resultSTO;
    }

    public STO
    doOperation(ConstSTO operand, Type resultType)
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
        return(new ErrorSTO("IncOp.doOperation()"));
    }

}
