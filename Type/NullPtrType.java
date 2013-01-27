//---------------------------------------------------------------------
// 
//---------------------------------------------------------------------


class NullPtrType extends PointerType
{
    // constructors
    public 
    NullPtrType (String strName, int size)
    {
        super(strName, size);
    }

    // methods
    public boolean isNullPtr() { return true; }

}
