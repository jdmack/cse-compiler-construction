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
    }

    public ExprSTO(String strName, Type typ)
    {
        super(strName, typ, false);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public boolean isExpr()
    {
        return true;
    }
}
