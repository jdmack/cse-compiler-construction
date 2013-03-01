import java.util.Vector;

public class ArrEleSTO extends STO
{
    private Vector<STO> exprs;

    public ArrEleSTO(Vector<STO> exprs)
    {
        super("ArrEleSTO", new VoidType(), false);
        this.exprs = exprs;
    }

    public Vector<STO> getArrayElements()
    {
        return exprs;
    }

    public boolean isArrEle()
    {
        return true;
    }
}
