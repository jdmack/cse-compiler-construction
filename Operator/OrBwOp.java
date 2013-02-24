//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class OrBwOp extends BitwiseOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public OrBwOp(String strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO doOperation(ConstSTO operand1, ConstSTO operand2, Type resultType)
    {
        Double value = new Double(operand1.getIntValue() | operand2.getIntValue());

        return new ConstSTO("OrBwOp.doOperation Result", resultType, value);
    }

}
