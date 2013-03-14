//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class BooleanOp extends BinaryOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public BooleanOp(String strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO checkOperands(STO operand1, STO operand2)
    {
        STO resultSTO;

        // Check #1 - Or, And - Both operands bool
        // Check operand1
        if(!operand1.getType().isBool()) {
            return(new ErrorSTO(Formatter.toString(ErrorMsg.error1w_Expr, operand1.getType().getName(), this.getName(), "bool")));
        }
        // Check operand2
        if(!operand2.getType().isBool()) {
            return(new ErrorSTO(Formatter.toString(ErrorMsg.error1w_Expr, operand2.getType().getName(), this.getName(), "bool")));
        }

        if(operand1.isConst() && operand2.isConst()) {
            resultSTO = new ConstSTO(getName() + " result", new BoolType());
        }
        else {
            resultSTO = new ExprSTO(getName() + "result", new BoolType());
        }

        return resultSTO;

    }

    public boolean isBooleanOp()
    {
        return true;
    }
}
