//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

import java.util.Vector;

class FuncPtrType extends PtrGrpType
{
    //----------------------------------------------------------------
    //    Instance variables.
    //----------------------------------------------------------------
    private Type m_returnType;
    private int m_numOfParams;
    private Vector<ParamSTO> m_parameters;
    private boolean m_returnByReference;

    //---------------------------------------------------------------------
    //      Constants
    //---------------------------------------------------------------------
    private static final String FUNCPTR_NAME = "funcptr";
    private static final int FUNCPTR_SIZE    = 8;

    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public FuncPtrType()
    {
        super(FUNCPTR_NAME, FUNCPTR_SIZE);
    }

    public FuncPtrType(String strName, int size)
    {
        super(strName, size);
    }

    public FuncPtrType(Type returnType, boolean returnByRef, Vector<ParamSTO> paramList)
    {
        super(FUNCPTR_NAME, FUNCPTR_SIZE);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public boolean isFuncPtr()
    {
        return true;
    }

    //////////////////////////////
    //      m_returnType        //
    //////////////////////////////
    public void setReturnType(Type typ)
    {
        m_returnType = typ;
    }

    public Type getReturnType()
    {
        return m_returnType;
    }

    //////////////////////////////
    //      m_numOfParams       //
    //////////////////////////////
    private void setNumOfParams(int numParams)
    {
        m_numOfParams = numParams;
    }

    public int getNumOfParams()
    {
        return m_numOfParams;
    }

    //////////////////////////////
    //      m_parameters        //
    //////////////////////////////
    public Vector<ParamSTO>
    getParameters()
    {
        return m_parameters;
    }

    public void setParameters(Vector<ParamSTO> params)
    {
        m_parameters = params;
        setNumOfParams(params.size());
    }

    //////////////////////////////
    //      m_returnByReference //
    //////////////////////////////
    private void setReturnByRef(boolean retByRef)
    {
        m_returnByReference = retByRef;
    }

    public boolean getReturnByRef()
    {
        return m_returnByReference;
    }




}
