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

        if(resultType.isInt())
            b_value = operand1.getIntValue() <= operand2.getIntValue();
        else if(resultType.isFloat())
            b_value = operand1.getFloatValue() <= operand2.getFloatValue();

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
