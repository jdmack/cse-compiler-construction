//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class NEqualToOp extends ComparisonOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    NEqualToOp(strName)
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

        // Check #1 - NotEqualTo - Both operands numeric
        if((!(operand1.getType().isNumeric() && operand2.getType().isNumeric())) && (!(operand1.getType().isBool() && operand2.getType().isBool())))
        {
            return (new ErrorSTO(Formatter.toString(ErrorMsg.error1b_Expr, operand1.getType().getName(), this.getName(), operand2.getType().getName()));
        }

        resultSTO = new ExprSTO("NEqualToOp.checkOperands() Result", new BoolType());

        return resultSTO;
    }
}
