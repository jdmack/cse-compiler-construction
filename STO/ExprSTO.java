//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class ExprSTO extends STO
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public ExprSTO(String strName)
    {
        super(strName, null, false);
        // You may want to change the isModifiable and isAddressable
        // fields as necessary

        // Don't need to because we want false and that's the initial value

    }

    public ExprSTO(String strName, Type typ)
    {
        super(strName, typ, false);
        // You may want to change the isModifiable and isAddressable
        // fields as necessary

        // Don't need to because we want false and that's the initial value
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public boolean isExpr()
    {
        return true;
    }
}
