//---------------------------------------------------------------------
// 
//---------------------------------------------------------------------


class PtrGrpType extends CompositeType
{
    // constructors
    public 
    PtrGrpType (String strName, int size)
    {
        super(strName, size);
    }

    // methods
    public boolean isPtrGrp() { return true; }

}
