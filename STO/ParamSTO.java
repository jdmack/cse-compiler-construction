//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class ParamSTO extends STO
{
    //---------------------------------------------------------------------
    //      Instance Variables
    //---------------------------------------------------------------------
    private boolean m_passByReference;
    private STO m_valueSto;

    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public ParamSTO(String strName, Type type, boolean passByRef)
    {
        super(strName, type, true);
        setPassByReference(passByRef);
        setValueSto(null);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------

    public boolean isPassByReference()
    {
        return m_passByReference;
    }

    private void setPassByReference(boolean passByRef)
    {
        m_passByReference = passByRef;
        setIsReference(passByRef);
    }

    public boolean isReference()
    {
        return super.isReference();
    }

    public void setIsReference(boolean ref)
    {
        super.setIsReference(ref);
    }

    public boolean isParam()
    {
        return true;
    }

    public STO getValueSto()
    {
        return m_valueSto;
    }

    private void setValueSto(STO value)
    {
        m_valueSto = value;
    }

}
