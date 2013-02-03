//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class AndOp extends BooleanOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    AndOp(String strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------

    public STO
    doOperation(ConstSTO operand1, ConstSTO operand2, Type resultType)
    {
        Double value = 0.0;
        boolean b_value = true;

        b_value = operand1.getBoolValue() && operand2.getBoolValue();

        if(b_value)
            value = new Double(1);
        else
            value = new Double(0);

        return new ConstSTO("AndOp.doOperation Result", resultType, value);
    }

}
