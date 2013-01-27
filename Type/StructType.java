//---------------------------------------------------------------------
// 
//---------------------------------------------------------------------

class StructType extends CompositeType
{
    // constructors
    public 
    StructType (String strName, int size)
    {
        super(strName, size);
    }

    // methods
    public boolean isStruct() { return true; }

}
