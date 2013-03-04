//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class NEqualToOp extends ComparisonOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public NEqualToOp(String strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO checkOperands(STO operand1, STO operand2)
    {
        STO resultSTO;
        Type o1Type = operand1.getType();
        Type o2Type = operand2.getType();
        
        // Check #1 - NotEqualTo - Both operands numeric
        if((!(o1Type.isNumeric() && o2Type.isNumeric())) &&(!(o1Type.isBool() && o2Type.isBool()))) {
        	// Check #17 The operand types must BOTH be of equivalent pointer type or one is of pointer type and the other is the type nullptr
        	if((!(o1Type.isEquivalent(o2Type) && o2Type.isEquivalent(o1Type))) && (!((o1Type.isPointer() && o2Type.isNullPtr()) || ((o1Type.isNullPtr() && o2Type.isPointer()))))) {
        		return(new ErrorSTO(Formatter.toString(ErrorMsg.error17_Expr, this.getName(), o1Type.getName(), o2Type.getName())));
        	}
        	return(new ErrorSTO(Formatter.toString(ErrorMsg.error1b_Expr, operand1.getType().getName(), this.getName(), operand2.getType().getName())));
        }

        if((operand1.isConst() && operand2.isConst()) || (o1Type.isNullPtr() && o2Type.isNullPtr())) {
            resultSTO = new ConstSTO("NEqualToOp.checkOperands() Result", new BoolType());
        }
        else {
            resultSTO = new ExprSTO("NEqualToOp.checkOperands() Result", new BoolType());
        }


        return resultSTO;
    }

    public STO doOperation(ConstSTO operand1, ConstSTO operand2, Type resultType)
    {
        Double value = 0.0;
        boolean b_value = true;

        if(resultType.isInt()) {
            b_value = operand1.getIntValue() != operand2.getIntValue();
        }
        else if(resultType.isFloat()) {
            b_value = operand1.getFloatValue() != operand2.getFloatValue();
        }
        else if(resultType.isBool()) {
            b_value = operand1.getBoolValue() != operand2.getBoolValue();
        }

        if(b_value)
            value = new Double(1);
        else
            value = new Double(0);

        return new ConstSTO("NEqualToOp.doOperation Result", resultType, value);
    }
}
