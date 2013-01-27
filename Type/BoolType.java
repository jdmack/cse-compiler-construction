//---------------------------------------------------------------------
// 
//---------------------------------------------------------------------

class BoolType extends BasicType
{
    // constants
    private static final String BOOL_NAME = "bool";
    private static final int BOOL_SIZE    = 1;

    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    public 
    BoolType ()
    {
        this(BOOL_NAME, BOOL_SIZE);
    }

    public 
    BoolType (String strName)
    {
        this(strName, BOOL_SIZE);
    }

    public 
    BoolType (String strName, int size)
    {
        super(strName, size);
    }

    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    public boolean isBasic() { return true; }

    //----------------------------------------------------------------
    //    
    //----------------------------------------------------------------
}
