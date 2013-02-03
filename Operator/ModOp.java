//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class ModOp extends ArithmeticOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    ModOp(String strName)
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

        // Check #1 - Modulus - Both operands int
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

        if(operand1.isConst() && operand2.isConst())
        {
            resultSTO = new ConstSTO("ModOp.checkOperands() Result", new IntType());
        }
        else
        {
            resultSTO = new ExprSTO("ModOp.checkOperands() Result", new IntType());
        }

        return resultSTO;
    }
    public STO
    doOperation(ConstSTO operand1, ConstSTO operand2, Type resultType)
    {
        Double value = 0.0;

            value = new Double(operand1.getIntValue() % operand2.getIntValue());

        return new ConstSTO("ModOp.doOperation Result", resultType, value);
    }

}
