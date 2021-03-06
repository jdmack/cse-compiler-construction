
//---------------------------------------------------------------------
//
//---------------------------------------------------------------------
import java.util.*;

class SymbolTable
{
    //----------------------------------------------------------------
    //    Instance variables.
    //----------------------------------------------------------------
    private Stack<Scope> m_stkScopes;
    private int          m_nLevel;
    private Scope        m_scopeGlobal;
    private Scope        m_scopeStatic;
    private FuncSTO      m_func = null;

    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    public SymbolTable()
    {
        m_nLevel = 0;
        m_stkScopes = new Stack<Scope>();
        m_scopeGlobal = null;
    }

    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    public void insert(STO sto)
    {
        Scope scope = m_stkScopes.peek();

        // if scope is global, setIsGlobal on sto
        if(isGlobalScope())
            sto.setIsGlobal(true);

        // If it's a static
        /*
        if(sto.isStatic())
            m_scopeStatic.Insert(sto);

        */
        scope.InsertLocal(sto);

    }

    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    public STO accessGlobal(String strName)
    {
        return(m_scopeGlobal.access(strName));
    }

    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    public STO accessLocal(String strName)
    {
        Scope scope = m_stkScopes.peek();

        return(scope.accessLocal(strName));
    }

    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    public STO access(String strName)
    {
        Stack stk = new Stack();
        Scope scope;
        STO stoReturn = null;

        stk.addAll(m_stkScopes);
        Collections.reverse(stk);

        for(Enumeration<Scope> e = stk.elements(); e.hasMoreElements(); ) {
            scope = e.nextElement();
            if((stoReturn = scope.access(strName)) != null)
                return    stoReturn;
        }

        return(null);
    }

    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    public void openScope()
    {
        Scope scope = new Scope();

        // The first scope created will be the global scope.
        if(m_scopeGlobal == null)
            m_scopeGlobal = scope;

        m_stkScopes.push(scope);
        // m_nLevel = 1 is Global Scope
        m_nLevel++;         
    }

    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    public void closeScope()
    {
        m_stkScopes.pop();
        m_nLevel--;
    }

    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    public int getLevel()
    {
        return m_nLevel;
    }

    public boolean isGlobalScope()
    {
        if(m_nLevel == 1)
            return true;
        else
            return false;
    }

    //----------------------------------------------------------------
    //    This is the function currently being parsed.
    //----------------------------------------------------------------
    public FuncSTO getFunc()
    {
        return m_func;
    }
    public void setFunc(FuncSTO sto)
    {
        m_func = sto;
    }
}
