//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class ArithmeticOp extends BinaryOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    ArithmeticOp ()
    {
        super();
    }
    
    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO
    checkOperands(STO op1, STO op2)
    {
        STO newSTO;

        // Check #1 - Both operands numeric
        // Check left operand to be numeric
        if(!operand1.getType().isNumeric())
        {
            m_nNumErrors++;
            m_errors.print (Formatter.toString(ErrorMsg.error1w_Expr, operand1.getType().getName(), op));    
            return (new ErrorSTO (operand1.getName()));
        }
        // Check right operand to be numeric
        else if((!operand2.getType().isNumeric()))
        {
            m_nNumErrors++;
            m_errors.print (Formatter.toString(ErrorMsg.error1w_Expr, operand2.getType().getName(), op));    
            return (new ErrorSTO (operand2.getName()));
        }
        
        // Check successful, determine result type
        // Plus, Minus, Star, Slash - Int if both int, Float otherwise
        if(operand1.getType().isInt() && operand2.getType().isInt())
        {
            newSTO = new ExprSTO("DoBinaryOp Result", new IntType());
        }
        else
        {
            newSTO = new ExprSTO("DoBinaryOp Result", new FloatType());
        }

        return (newSTO);
    }
}
