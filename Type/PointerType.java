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
    //      Instance Variables
    //---------------------------------------------------------------------
    private Type m_pointsToType;

    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public PointerType()
    {
        super("null*", POINTER_SIZE);
        m_pointsToType = null;

    }

    public PointerType(Type pointsTo)
    {
        super(POINTER_NAME, POINTER_SIZE);
        m_pointsToType = pointsTo;

        String pointerName;

        if(pointsTo == null)
            pointerName = "null*";
        else
            pointerName = pointsTo.getName() + "*";
        setName(pointerName);
    }

    public PointerType(String name, int size, Type pointsTo)
    {
        super(POINTER_NAME, POINTER_SIZE);
        m_pointsToType = pointsTo;

        String pointerName;

        if(pointsTo == null)
            pointerName = "null*";
        else
            pointerName = pointsTo.getName() + "*";
        setName(pointerName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public boolean isPointer()
    {
        return true;
    }


    public Type getPointsToType()
    {
        return m_pointsToType;
    }
    public void setPointsToType(Type pointsTo)
    {
        m_pointsToType = pointsTo;
        if(pointsTo != null)
            setName(pointsTo.getName() + "*");
    }

    public Type getBottomPtrType()
    {
        if(m_pointsToType == null)
            return null;

        if(m_pointsToType.isPointer())
            return ((PointerType) m_pointsToType).getBottomPtrType();
        else
            return m_pointsToType;
    }

    public void setBottomPtrType(Type newType)
    {
        if(m_pointsToType == null)
            setPointsToType(newType);

        if(m_pointsToType.isPointer())
            ((PointerType) m_pointsToType).setBottomPtrType(newType);
        else
            setPointsToType(newType);
    }

    public String setInitialName()
    {
        if(m_pointsToType == null)
            setName("nullptr");
        else if(m_pointsToType.isArray())
            setName(m_pointsToType.getName()); // TODO: TEMPORARY TO MAKE COMPILER
        else if(m_pointsToType.isPointer())
            setName(((PointerType) m_pointsToType).setInitialName() + "*");
        else
            setName(m_pointsToType.getName() + "*");

        return getName();

    }

    // meaning: param = this
    public boolean isAssignable(Type type)
    {
        if(type == null)
            return false;
        else if(type.isPointer())
            return m_pointsToType.isAssignable(((PointerType) type).getPointsToType());
        else
            return isEquivalent(type);
    }
}
