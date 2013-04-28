//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class BitwiseOp extends BinaryOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public BitwiseOp(String strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO checkOperands(STO operand1, STO operand2)
    {
        STO resultSTO;

        // Check #1 - %, ^, | - both operands int
        // Check left operand to be int
        if((!operand1.getType().isInt())) {
            return(new ErrorSTO(Formatter.toString(ErrorMsg.error1w_Expr, operand1.getType().getName(), this.getName(), "int")));
        }
        // Check right operand to be int
        else if((!operand2.getType().isInt())) {
            return(new ErrorSTO(Formatter.toString(ErrorMsg.error1w_Expr, operand2.getType().getName(), this.getName(), "int")));
        }

        if(operand1.isConst() && operand2.isConst()) {
            resultSTO = new ConstSTO(operand1.getName() + "|" + operand2.getName() + " Result", new IntType());
        }
        else {
            resultSTO = new ExprSTO(operand1.getName() + "|" + operand2.getName() + " Result", new IntType());
        }


        return resultSTO;
    }

    public boolean isBitwiseOp()
    {
        return true;
    }
}
