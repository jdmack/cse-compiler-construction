//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class BoolType extends BasicType
{
    //---------------------------------------------------------------------
    //      Constants
    //---------------------------------------------------------------------
    private static final String BOOL_NAME = "bool";
    private static final int BOOL_SIZE    = 4;

    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public BoolType()
    {
        this(BOOL_NAME, BOOL_SIZE);
    }

    public BoolType(String strName)
    {
        this(strName, BOOL_SIZE);
    }

    public BoolType(String strName, int size)
    {
        super(strName, size);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public boolean isBool()
    {
        return true;
    }

}
