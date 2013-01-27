//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class LessThanOp extends ComparisonOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    LessThanOp(strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO
    checkOperands(STO operand1, STO operand2)
    {

        // Check #1 - Both operands numeric
        // Check left operand to be numeric
        if((!operand1.getType().isNumeric()))
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
        if(op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/"))
        {
            if(operand1.getType().isInt() && operand2.getType().isInt())
            {
                sto = new ExprSTO("DoBinaryOp Result", new IntType());
            }
            else
            {
                sto = new ExprSTO("DoBinaryOp Result", new FloatType());
            }
        }

        // Relation operators
        else if(op.equals(">") || op.equals("<") || op.equals(">=") || op.equals("<="))
        {
            sto = new ExprSTO("DoBinaryOp Result", new BoolType());
        }
        else
        {
            sto = new ErrorSTO("DoBinaryOp Bad Operator");
        }

        else if(op.equals("==") || op.equals("!="))
        {
            if((!(operand1.getType().isNumeric() && operand2.getType().isNumeric())) && (!(operand1.getType().isBool() && operand2.getType().isBool())))
            {
                m_nNumErrors++;
                m_errors.print (Formatter.toString(ErrorMsg.error1b_Expr, operand1.getType().getName(), op, operand2.getType().getName()));    
                return (new ErrorSTO (operand1.getName()));
            }
            else
            {
                sto = new ExprSTO("DoBinaryOp Result", new BoolType());   
            }
        }

        else
        {
            sto = new ErrorSTO("DoBinaryOp Bad Operator");
        }

        return (sto);
    }
}
