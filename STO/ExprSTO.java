//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class ExprSTO extends STO
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    ExprSTO (String strName)
    {
        super (strName);
                // You may want to change the isModifiable and isAddressable                      
                // fields as necessary
    }

    public 
    ExprSTO (String strName, Type typ)
    {
        super (strName, typ);
                // You may want to change the isModifiable and isAddressable                      
                // fields as necessary
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public boolean
    isExpr ()
    {
        return    true;
    }
}
