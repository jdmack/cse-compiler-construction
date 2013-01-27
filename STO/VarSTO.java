//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class VarSTO extends STO
{
    // constructors
    public 
    VarSTO (String strName)
    {
        super (strName);
        // You may want to change the isModifiable and isAddressable 
        // fields as necessary
    }

    public 
    VarSTO (String strName, Type typ)
    {
        super (strName, typ);
        // You may want to change the isModifiable and isAddressable 
        // fields as necessary
    }

    // methods
    public boolean   
    isVar () 
    {
        return true;
    }
}
