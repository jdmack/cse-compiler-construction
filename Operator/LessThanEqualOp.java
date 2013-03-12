//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class LessThanEqualOp extends ComparisonOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public LessThanEqualOp(String strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------

    public STO doOperation(ConstSTO operand1, ConstSTO operand2, Type resultType)
    {
        Double value = 0.0;
        boolean b_value = true;

        if(operand1.getType().isFloat() || operand2.getType().isFloat()) {
            b_value = operand1.getFloatValue() <= operand2.getFloatValue();
        }

        else {
            b_value = operand1.getIntValue() <= operand2.getIntValue();
        }

        if(b_value)
            value = new Double(1);
        else
            value = new Double(0);

        return new ConstSTO("LessThanEqualOp.doOperation Result", resultType, value);
    }


    public boolean isLessThanEqualOp()
    {
        return true;
    }
}
