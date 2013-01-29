//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class ParamSTO extends STO
{
    //---------------------------------------------------------------------
    //      Instance Variables
    //---------------------------------------------------------------------
    private boolean m_passByReference; 

    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    ParamSTO(String strName, Type type, boolean passByRef)
    {
        super(strName, type);

    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public boolean
    getPassByReference()
    {
        return m_passByReference;
    }

    private void
    setPassByReference(boolean passByRef)
    {
        m_passByReference = passByRef;
    }

    public boolean   
    isParam() 
    {
        return true;
    }
}
