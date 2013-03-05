//---------------------------------------------------------------------
//
//---------------------------------------------------------------------
import java.util.Vector;

class FuncSTO extends STO
{
    //----------------------------------------------------------------
    //    Instance variables.
    //----------------------------------------------------------------

    private boolean m_hasReturnStatement;
    private int     m_level;

    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------

    public FuncSTO(String strName, FuncPtrType funcPtr)
    {
        super(strName, funcPtr);

        setHasReturnStatement(false);
        setLevel(0);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public boolean isFunc()
    {
        return true;
    }

    //----------------------------------------------------------------
    // This is the return type of the function. This is different from
    // the function's type(for function pointers).
    //----------------------------------------------------------------

    //////////////////////////////
    //      m_returnType        //
    //////////////////////////////
    public void setReturnType(Type typ)
    {
        ((FuncPtrType) getType()).setReturnType(typ);
    }

    public Type getReturnType()
    {
        return ((FuncPtrType) getType()).getReturnType();
    }

    //////////////////////////////
    //      m_numOfParams       //
    //////////////////////////////
    private void setNumOfParams(int numParams)
    {
        ((FuncPtrType) getType()).setNumOfParams(numParams);
    }

    public int getNumOfParams()
    {
        return ((FuncPtrType) getType()).getNumOfParams();
    }

    //////////////////////////////
    //      m_parameters        //
    //////////////////////////////
    public Vector<ParamSTO> getParameters()
    {
        return ((FuncPtrType) getType()).getParameters();
    }

    public void setParameters(Vector<ParamSTO> params)
    {
        ((FuncPtrType) getType()).setParameters(params);
        setNumOfParams(params.size());
    }

    //////////////////////////////
    //      m_returnByReference //
    //////////////////////////////
    private void setReturnByRef(boolean retByRef)
    {
        ((FuncPtrType) getType()).setReturnByRef(retByRef);
    }

    public boolean getReturnByRef()
    {
        return ((FuncPtrType) getType()).getReturnByRef();
    }

    //////////////////////////////
    //     m_hasReturnStatement //
    //////////////////////////////
    public void setHasReturnStatement(boolean hasRtnStmt)
    {
        m_hasReturnStatement = hasRtnStmt;
    }

    public boolean getHasReturnStatement()
    {
        return m_hasReturnStatement;
    }

    //////////////////////////////
    //      m_level             //
    //////////////////////////////
    public void setLevel(int level)
    {
        m_level = level;
    }

    public int getLevel()
    {
        return m_level;
    }
}
