//---------------------------------------------------------------------
// 
//---------------------------------------------------------------------


class NumericType extends BasicType
{
    // constructors
    public 
    NumericType (String strName, int size)
    {
        super(strName, size);
    }

    // methods
    public boolean isNumeric() { return true; }

}
