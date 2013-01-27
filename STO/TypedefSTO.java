//---------------------------------------------------------------------
// For typedefs like: typedef int myInteger1, myInteger2;
// Also can hold the structdefs
//---------------------------------------------------------------------

class TypedefSTO extends STO
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    TypedefSTO (String strName)
    {
        super (strName);
    }

    public 
    TypedefSTO (String strName, Type typ)
    {
        super (strName, typ);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public boolean
    isTypedef ()
    {
        return true;
    }
}
