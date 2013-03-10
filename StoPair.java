//---------------------------------------------------------------------
//      StoPair - encapsulates two stos
//---------------------------------------------------------------------

public class StoPair
{

    //---------------------------------------------------------------------
    //      Instance Variables
    //---------------------------------------------------------------------
    private STO m_varSto;
    private STO m_valueSto;

    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------

    public StoPair(STO var, STO value)
    {
        setVarSto(var);
        setValueSto(value);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------

    public STO getVarSto()
    {
        return m_varSto;
    }

    public void setVarSto(STO var)
    {
        m_varSto = var;
    }

    public STO getValueSto()
    {
        return m_valueSto;
    }

    public void setValueSto(STO value)
    {
        m_valueSto = value;
    }
}
