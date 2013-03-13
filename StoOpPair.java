//---------------------------------------------------------------------
//      StoOpPair - encapsulates two stos
//---------------------------------------------------------------------

public class StoOpPair
{

    //---------------------------------------------------------------------
    //      Instance Variables
    //---------------------------------------------------------------------
    private STO m_sto;
    private Operator m_op;

    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------

    public StoOpPair(STO var, Operator op)
    {
        setSto(var);
        setOp(op);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------

    public STO getSto()
    {
        return m_sto;
    }

    public void setSto(STO var)
    {
        m_sto = var;
    }

    public Operator getOp()
    {
        return m_op;
    }

    public void setOp(Operator op)
    {
        m_op = op;
    }
}
