//---------------------------------------------------------------------
// This is the top of the Type hierarchy. You most likely will need to
// create sub-classes (since this one is abstract) that handle specific
// types, such as IntType, FloatType, ArrayType, etc.
//---------------------------------------------------------------------


class VoidType extends Type
{
    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    public 
    VoidType (String strName, int size)
    {
        super(strName, size);     
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------


    //----------------------------------------------------------------
    //    
    //----------------------------------------------------------------
    public boolean isVoid() { return true; }
}
