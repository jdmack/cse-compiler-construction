//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class BooleanOp extends BinaryOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    BooleanOp(String strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO
    checkOperands(STO operand1, STO operand2)
    {
        STO resultSTO;

        // Check #1 - Or, And - Both operands bool
        // Check operand1
        if(!operand1.getType().isBool())
        {
            return(new ErrorSTO(Formatter.toString(ErrorMsg.error1w_Expr, operand1.getType().getName(), this.getName(), "bool")));
        }
        // Check operand2
        if(!operand2.getType().isBool())
        {
            return(new ErrorSTO(Formatter.toString(ErrorMsg.error1w_Expr, operand2.getType().getName(), this.getName(), "bool")));
        }

        resultSTO = new ExprSTO("NEqualToOp.checkOperands() Result", new BoolType());

        return resultSTO;

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
        return(new ErrorSTO("BooleanOp.doOperation()"));
    }

}
