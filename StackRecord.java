//---------------------------------------------------------------------
//      StackRecord
//---------------------------------------------------------------------

public class StackRecord
{

    //---------------------------------------------------------------------
    //      Instance Variables
    //---------------------------------------------------------------------
    private String m_function;
    private String m_id;
    private String m_location;

    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------

    public StackRecord(String function, String id, String location)
    {
        setFunction(function);
        setId(id);
        set(location);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------

    public String getFunction()
    {
        return m_function;
    }

    public void setFunction(String function)
    {
        m_function = function;
    }

    public String getId()
    {
        return m_id;
    }

    public void setId(String id)
    {
        m_id = id;
    }

    public String getLocation()
    {
        return m_location;
    }

    public void setLocation(String location)
    {
        m_location = location;
    }
}
