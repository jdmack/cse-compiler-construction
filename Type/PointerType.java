//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class PointerType extends PtrGrpType
{
    //---------------------------------------------------------------------
    //      Constants
    //---------------------------------------------------------------------
    private static final String POINTER_NAME = "pointer";
    private static final int POINTER_SIZE    = 4;

    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public PointerType()
    {
        super(POINTER_NAME, POINTER_SIZE, null);
    }

    public PointerType(Type pointsTo)
    {
        super(POINTER_NAME, POINTER_SIZE, pointsTo);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public boolean isPointer()
    {
        return true;
    }

}
