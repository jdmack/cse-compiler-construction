//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class ComparisonOp extends BinaryOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    ComparisonOp(String strName)
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

        // Check #1 - LessThan, LessThanEqual, GreaterThan, GreaterThanEqual - Both operands numeric
        // Check left operand to be numeric
        if((!operand1.getType().isNumeric()))
        {
            return(new ErrorSTO(Formatter.toString(ErrorMsg.error1n_Expr, operand1.getType().getName(), this.getName())));
        }
        // Check right operand to be numeric
        else if((!operand2.getType().isNumeric()))
        {
            return(new ErrorSTO(Formatter.toString(ErrorMsg.error1n_Expr, operand2.getType().getName(), this.getName())));
        }

        // Check successful, determine result type
        resultSTO = new ExprSTO("ComparisonOp.checkOperands() Result", new BoolType());

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
        return(new ErrorSTO("ComparisonOp.doOperation()"));
    }

}
