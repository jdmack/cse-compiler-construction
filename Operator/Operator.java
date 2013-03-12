//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

abstract class Operator
{
    //---------------------------------------------------------------------
    //      Instance Variables
    //---------------------------------------------------------------------
    private String m_opName;


    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public Operator(String strName)
    {
        setName(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public String getName()
    {
        return m_opName;
    }

    public void setName(String str)
    {
        m_opName = str;
    }

    //---------------------------------------------------------------------
    //      Is Methods
    //---------------------------------------------------------------------
    public boolean isOperator()
    {
        return true;
    }
    public boolean isAddOp()
    {
        return false;
    }
    public boolean isAddressOfOp()
    {
        return false;
    }
    public boolean isAndBwOp()
    {
        return false;
    }
    public boolean isAndOp()
    {
        return false;
    }
    public boolean isArithmeticOp()
    {
        return false;
    }
    public boolean isArrowOp()
    {
        return false;
    }
    public boolean isBinaryOp()
    {
        return false;
    }
    public boolean isBitwiseOp()
    {
        return false;
    }
    public boolean isBooleanOp()
    {
        return false;
    }
    public boolean isComparisonOp()
    {
        return false;
    }
    public boolean isDecOp()
    {
        return false;
    }
    public boolean isDerefOp()
    {
        return false;
    }
    public boolean isDivOp()
    {
        return false;
    }
    public boolean isEqualToOp()
    {
        return false;
    }
    public boolean isGreaterThanEqualOp()
    {
        return false;
    }
    public boolean isGreaterThanOp()
    {
        return false;
    }
    public boolean isIncOp()
    {
        return false;
    }
    public boolean isLessThanEqualOp()
    {
        return false;
    }
    public boolean isLessThanOp()
    {
        return false;
    }
    public boolean isMinusOp()
    {
        return false;
    }
    public boolean isModOp()
    {
        return false;
    }
    public boolean isMulOp()
    {
        return false;
    }
    public boolean isNEqualToOp()
    {
        return false;
    }
    public boolean isNotOp()
    {
        return false;
    }
    public boolean isOrBwOp()
    {
        return false;
    }
    public boolean isOrOp()
    {
        return false;
    }
    public boolean isUnaryOp()
    {
        return false;
    }
    public boolean isUnMinusOp()
    {
        return false;
    }
    public boolean isUnPlusOp()
    {
        return false;
    }
    public boolean isXorOp()
    {
        return false;
    }
}
