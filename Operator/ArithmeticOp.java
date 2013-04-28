//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class ArithmeticOp extends BinaryOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public ArithmeticOp(String strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO checkOperands(STO operand1, STO operand2)
    {
        STO resultSTO;

        // Check #1 - Plus, Minus, Mul, Div - Both operands numeric
        // Check left operand to be numeric
        if(!operand1.getType().isNumeric()) {
            return(new ErrorSTO(Formatter.toString(ErrorMsg.error1n_Expr, operand1.getType().getName(), this.getName())));
        }
        // Check right operand to be numeric
        else if((!operand2.getType().isNumeric())) {
            return(new ErrorSTO(Formatter.toString(ErrorMsg.error1n_Expr, operand2.getType().getName(), this.getName())));
        }

        // Check successful, determine result type
        // Plus, Minus, Star, Slash - Int if both int, Float otherwise
        Type resultType;

        if(operand1.getType().isInt() && operand2.getType().isInt()) {
            resultType = new IntType();
        }
        else {
            resultType = new FloatType();
        }

        if(operand1.isConst() && operand2.isConst()) {
            resultSTO = new ConstSTO(operand1.getName() + "+-*/" + operand2.getName() + " Result", resultType);
        }
        else {
            resultSTO = new ExprSTO("ArithmeticOp.checkOperands() Result", resultType);
        }

        return resultSTO;
    }

    public boolean isArithmeticOp()
    {
        return true;
    }
}
