//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class EqualToOp extends ComparisonOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    EqualToOp(String strName)
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

        // Check #1 - EqualTo - Both operands numeric
        if((!(operand1.getType().isNumeric() && operand2.getType().isNumeric())) &&(!(operand1.getType().isBool() && operand2.getType().isBool())))
        {
            return(new ErrorSTO(Formatter.toString(ErrorMsg.error1b_Expr, operand1.getType().getName(), this.getName(), operand2.getType().getName())));
        }

        resultSTO = new ExprSTO("EqualToOp.checkOperands() Result", new BoolType());

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
        return(new ErrorSTO("EqualToOp.doOperation()"));
    }

}
