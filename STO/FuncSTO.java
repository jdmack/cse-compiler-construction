//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class FuncSTO extends STO
{
    //----------------------------------------------------------------
    //    Instance variables.
    //----------------------------------------------------------------
    private Type         m_returnType;

    // constructors
    public 
    FuncSTO (String strName)
    {
        super (strName);
        setReturnType (null);
                // You may want to change the isModifiable and isAddressable                      
                // fields as necessary
    }


    // methods
    public boolean
    isFunc () 
    { 
        return true;
                // You may want to change the isModifiable and isAddressable                      
                // fields as necessary
    }


    //----------------------------------------------------------------
    // This is the return type of the function. This is different from 
    // the function's type (for function pointers).
    //----------------------------------------------------------------
    public void
    setReturnType (Type typ)
    {
        m_returnType = typ;
    }

    public Type
    getReturnType ()
    {
        return m_returnType;
    }


}
