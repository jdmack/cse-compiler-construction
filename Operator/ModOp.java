//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class ModOp extends ArithmeticOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    ModOp ()
    {
        super();
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO
    checkOperands (STO op1, STO op2)
    {
        STO newSTO;

        // Check #1 - Modulus - both int
        // Check left operand to be int
        if((!operand1.getType().isInt()))
        {
            m_nNumErrors++;
            m_errors.print (Formatter.toString(ErrorMsg.error1w_Expr, operand1.getType().getName(), op, "int"));    
            return (new ErrorSTO (operand1.getName()));
        }
        // Check right operand to be int
        else if((!operand2.getType().isInt()))
        {
            m_nNumErrors++;
            m_errors.print (Formatter.toString(ErrorMsg.error1w_Expr, operand2.getType().getName(), op, "int"));    
            return (new ErrorSTO (operand2.getName()));
        }

        newSTO = new ExprSTO("DoBinaryOp Result", new IntType());

        return newSTO;
    }
}
