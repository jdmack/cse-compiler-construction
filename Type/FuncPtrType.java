//---------------------------------------------------------------------
// 
//---------------------------------------------------------------------

class FuncPtrType extends PtrGrpType
{
    // constructors
    public 
    FuncPtrType (String strName, int size)
    {
        super(strName, size);
    }

    // methods
    public boolean isFuncPtr() { return true; }

}
