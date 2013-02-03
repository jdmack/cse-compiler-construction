//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class NotOp extends UnaryOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    NotOp(String strName)
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

        // Check #1 - Not - operand bool
        // Check operand
        if(!operand.getType().isBool())
        {
            return(new ErrorSTO(Formatter.toString(ErrorMsg.error1u_Expr, operand.getType().getName(), this.getName(), "bool")));
        }

        resultSTO = new ExprSTO("NotOp.checkOperands() Result", new BoolType());

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
        return(new ErrorSTO("NotOp.doOperation()"));
    }

}
