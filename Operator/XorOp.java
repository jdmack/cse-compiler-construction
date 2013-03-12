//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class XorOp extends BitwiseOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public XorOp(String strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO doOperation(ConstSTO operand1, ConstSTO operand2, Type resultType)
    {
        Double value = new Double(operand1.getIntValue() ^ operand2.getIntValue());

        return new ConstSTO("XorOp.doOperation Result", resultType, value);
    }

    public boolean isXorOp()
    {
        return true;
    }
}
