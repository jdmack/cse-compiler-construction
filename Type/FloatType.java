//---------------------------------------------------------------------
// 
//---------------------------------------------------------------------

class FloatType extends NumericType
{

    // Constants
    private static final String FLOAT_NAME = "float";
    private static final int FLOAT_SIZE    = 4;

    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    FloatType ()
    {
        this(FLOAT_NAME, FLOAT_SIZE);
    }

    public 
    FloatType (String strName)
    {
        this(strName, FLOAT_SIZE);
    }

    public 
    FloatType (String strName, int size)
    {
        super(strName, size);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public boolean isFloat() { return true; }

}
