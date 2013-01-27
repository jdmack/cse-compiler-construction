//---------------------------------------------------------------------
// 
//---------------------------------------------------------------------


class CompositeType extends Type
{
    // constructors
    public 
    CompositeType (String strName, int size)
    {
        super(strName, size);
    }

    // methods
    public boolean isComposite() { return true; }

}
