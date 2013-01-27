//---------------------------------------------------------------------
// 
//---------------------------------------------------------------------

class PointerType extends PtrGrpType
{
    // constructors
    public 
    PointerType (String strName, int size)
    {
        super(strName, size);
    }

    // methods
    public boolean isPointer() { return true; }

}
