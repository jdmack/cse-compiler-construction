//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class NotOp extends UnaryOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    NotOp(String strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO
    checkOperand(STO operand)
    {
        STO resultSTO;

        // Check #1 - Not - operand bool
        // Check operand
        if(!operand.getType().isBool())
        {
            return(new ErrorSTO(Formatter.toString(ErrorMsg.error1u_Expr, operand.getType().getName(), this.getName(), "bool")));
        }

        if(operand.isConst())
        {
            resultSTO = new ConstSTO("NotOp.checkOperands() Result", new BoolType());
        }
        else
        {
            resultSTO = new ExprSTO("NotOp.checkOperands() Result", new BoolType());
        }

        return resultSTO;
    }

    public STO
    doOperation(ConstSTO operand, Type resultType)
    {
        Double value = 0.0;
        boolean b_value = true;

        b_value = !(operand.getBoolValue());

        if(b_value)
            value = new Double(1);
        else
            value = new Double(0);

        return new ConstSTO("NotOp.doOperation Result", resultType, value);
    }
}
