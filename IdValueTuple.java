
public class IdValueTuple 
{

    //---------------------------------------------------------------------
    //      Instance Variables
    //---------------------------------------------------------------------
	String m_ID;
	STO m_value;
    STO m_arrayIndex;
	
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
	public 
    IdValueTuple(String ID, STO value) 
    {
        this(ID, value, null);
	}

	public 
    IdValueTuple(String ID, STO value, STO arrayIndex) 
    {
		m_ID = ID;
		m_value = value;
        m_arrayIndex = arrayIndex;
	}
	
    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
	public String 
    getId() 
    {
		return m_iD;
	}
	
	public STO 
    getValue() 
    {
		return m_value;
	}

	public STO 
    getArrayIndex() 
    {
		return m_arrayIndex;;
	}
}
