//----------------------------------------------------------------
//
//----------------------------------------------------------------
class VoidType extends Type
{
    // constructors
    public 
    VoidType (String strName, int size)
    {
        super(strName, size);     
    }

    //methods
    public boolean isVoid() { return true; }
}
