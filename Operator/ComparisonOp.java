//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class ComparisonOp extends BinaryOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public ComparisonOp(String strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO checkOperands(STO operand1, STO operand2)
    {
        STO resultSTO;

        // Check #1 - LessThan, LessThanEqual, GreaterThan, GreaterThanEqual - Both operands numeric
        // Check left operand to be numeric
        if((!operand1.getType().isNumeric())) {
            return(new ErrorSTO(Formatter.toString(ErrorMsg.error1n_Expr, operand1.getType().getName(), this.getName())));
        }
        // Check right operand to be numeric
        else if((!operand2.getType().isNumeric())) {
            return(new ErrorSTO(Formatter.toString(ErrorMsg.error1n_Expr, operand2.getType().getName(), this.getName())));
        }

        // Check successful, determine result type
        if(operand1.isConst() && operand2.isConst()) {
            resultSTO = new ConstSTO("ComparisonOp.checkOperands() Result", new BoolType());
        }
        else {
            resultSTO = new ExprSTO("ComparisonOp.checkOperands() Result", new BoolType());
        }

        return resultSTO;

    }
}
