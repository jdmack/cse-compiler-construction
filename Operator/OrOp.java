//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class OrOp extends BooleanOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public OrOp(String strName)
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

        b_value = operand1.getBoolValue() || operand2.getBoolValue();

        if(b_value)
            value = new Double(1);
        else
            value = new Double(0);

        return new ConstSTO("OrOp.doOperation Result", resultType, value);
    }

    public boolean isOrOp()
    {
        return true;
    }
}
