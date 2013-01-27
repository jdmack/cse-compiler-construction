//---------------------------------------------------------------------
// 
//---------------------------------------------------------------------

class ArrayType extends CompositeType
{
    // constructors
    public 
    ArrayType (String strName, int size)
    {
        super(strName, size);
    }

    // methods
    public boolean isArray() { return true; }
}
