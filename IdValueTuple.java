public class IdValueTuple
{

    //---------------------------------------------------------------------
    //      Instance Variables
    //---------------------------------------------------------------------
    private String m_ID;
    private STO m_value;
    private STO m_arrayIndex;
    private Type m_pointerType;

    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public IdValueTuple(String ID, STO value)
    {
        this(ID, value, null);
    }

    public IdValueTuple(String ID, STO value, STO arrayIndex)
    {
        this(ID, value, arrayIndex, null);
    }

    public IdValueTuple(String ID, STO value, STO arrayIndex, Type pointer)
    {
        m_ID = ID;
        m_value = value;
        m_arrayIndex = arrayIndex;
        m_pointerType = pointer;
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public String getId()
    {
        return m_ID;
    }

    public STO getValue()
    {
        return m_value;
    }

    public STO getArrayIndex()
    {
        return m_arrayIndex;
    }

    public Type getPointerType()
    {
        return m_pointerType;
    }
}
