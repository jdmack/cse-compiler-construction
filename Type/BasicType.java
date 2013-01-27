//---------------------------------------------------------------------
// 
//---------------------------------------------------------------------


class BasicType extends Type
{
    // constructors
    public 
    BasicType (String strName, int size)
    {
        super(strName, size);
    }

    // methods
    public boolean isBasic() { return true; }

}
