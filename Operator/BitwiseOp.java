//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class BitwiseOp extends BinaryOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    BitwiseOp(String strName)
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
    
        // Check #1 - %, ^, | - both operands int
        // Check left operand to be int
        if((!operand1.getType().isInt()))
        {
            return(new ErrorSTO(Formatter.toString(ErrorMsg.error1w_Expr, operand1.getType().getName(), this.getName(), "int")));
        }
        // Check right operand to be int
        else if((!operand2.getType().isInt()))
        {
            return(new ErrorSTO(Formatter.toString(ErrorMsg.error1w_Expr, operand2.getType().getName(), this.getName(), "int")));
        }

        resultSTO = new ExprSTO("BitwiseOp.checkOperands() Result", new IntType());

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
        return(new ErrorSTO("BitwiseOp.doOperation()"));
    }

}
