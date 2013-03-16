//---------------------------------------------------------------------/
//---------------------------------------------------------------------

abstract class STO
{
    //---------------------------------------------------------------------
    //      Instance Variables
    //---------------------------------------------------------------------
    private String  m_strName;
    private Type    m_type;
    private boolean m_isAddressable;
    private boolean m_isModifiable;
    private boolean m_isGlobal;
    private boolean m_isStatic;
    private boolean m_staticInit;
    private String  m_base;
    private String  m_offset;
    private boolean m_isInMemory;
    private boolean m_isReference;

    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------

    public STO(STO copySto)
    {
        setName(copySto.getName());
        setType(copySto.getType());
        setOffset("");
        setBase("");
        setIsAddressable(true);
        setIsModifiable(true);
        setIsGlobal(false);
        setIsStatic(false);
        setStaticInit(false);
        setIsInMemory(false);
        setIsReference(false);
    }

    public STO(String strName, Type type, boolean isModLvalue)
    {
        this(strName, type);
        setIsModLValue(isModLvalue);
    }

    public STO(String strName, Type type, boolean addressable, boolean modifiable)
    {
        this(strName, type);
        setIsAddressable(addressable);
        setIsModifiable(modifiable);
    }

    public STO(String strName, Type type)
    {
        setName(strName);
        setType(type);
        setOffset("");
        setBase("");
        setIsAddressable(false);
        setIsModifiable(false);
        setIsGlobal(false);
        setIsStatic(false);
        setStaticInit(false);
        setIsInMemory(false);
        setIsReference(false);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public String getName()
    {
        return m_strName;
    }

    private void setName(String str)
    {
        m_strName = str;
    }

    public Type getType()
    {
        return m_type;
    }

    private void setType(Type type)
    {
        m_type = type;
    }


    //----------------------------------------------------------------
    // Addressable refers to if the object has an address. Variables
    // and declared constants have an address, whereas results from
    // expression like(x + y) and literal constants like 77 do not
    // have an address.
    //----------------------------------------------------------------
    public boolean getIsAddressable()
    {
        return m_isAddressable;
    }

    private void
    setIsAddressable(boolean addressable)
    {
        m_isAddressable = addressable;
    }

    //----------------------------------------------------------------
    // You shouldn't need to use these two routines directly
    //----------------------------------------------------------------
    private boolean getIsModifiable()
    {
        return m_isModifiable;
    }

    private void
    setIsModifiable(boolean modifiable)
    {
        m_isModifiable = modifiable;
    }

    //----------------------------------------------------------------
    // A modifiable L-value is an object that is both addressable and
    // modifiable. Objects like constants are not modifiable, so they
    // are not modifiable L-values.
    //----------------------------------------------------------------
    public boolean isModLValue()
    {
        return getIsModifiable() && getIsAddressable();
    }

    protected void
    setIsModLValue(boolean m)
    {
        setIsModifiable(m);
        setIsAddressable(m);
    }

    //---------------------------------
    //      Base and Offset
    //---------------------------------

    public String getBase()
    {
        return m_base;
    }

    public void setBase(String base)
    {
        m_base = base;
    }

    public String getOffset()
    {
        return m_offset;
    }

    public void setOffset(String offset)
    {
        m_offset = offset;
    }
    
    // Load and store just do a get and set for both at same time
    public String load() throws NumberFormatException
    {
        //System.out.println(getName());
        //System.out.println("offset: " + m_offset);
        //System.out.println("base: " + m_base);
        if(isGlobal()) {
            return m_offset;
        }

        else {
            int offset = Integer.parseInt(m_offset);

            if(offset > 0)
                return m_base + "+" + m_offset;
            else
                return m_base + m_offset;

        }

    }

    public void store(String base, String offset)
    {
        m_base = base;
        m_offset = offset;
    }

    public boolean isInMemory()
    {
        if(getOffset().isEmpty() || getBase().isEmpty())
            return false;
        else
            return true;
    }

    public void setIsInMemory(boolean value)
    {
        m_isInMemory = value;
    }

    public boolean isGlobal()
    {
        return m_isGlobal;
    }

    public void setIsGlobal(boolean global)
    {
        m_isGlobal = global;
    }
    
    public boolean isReference()
    {
        return m_isReference;
    }

    public void setIsReference(boolean isReference)
    {
        m_isReference = isReference;
    }

    public boolean getIsStatic()
    {
        return m_staticInit;
    }

    public void setIsStatic(boolean isStatic)
    {
        m_isStatic = isStatic;
    }

    public boolean getStaticInit()
    {
        return m_staticInit;
    }

    public void setStaticInit(boolean init)
    {
        m_isStatic = init;
    }
    
    public void setGlobal(boolean global) 
    {
    	if(global) {
    		setBase("%g0");
    	}
    	else {
    		setBase("%fp");
    	}
    }
    
    //----------------------------------------------------------------
    //    It will be helpful to ask a STO what specific STO it is.
    //    The Java operator instanceof will do this, but these methods
    //    will allow more flexibility(ErrorSTO is an example of the
    //    flexibility needed).
    //----------------------------------------------------------------
    public boolean isVar()
    {
        return false;
    }
    public boolean isConst()
    {
        return false;
    }
    public boolean isExpr()
    {
        return false;
    }
    public boolean isFunc()
    {
        return false;
    }
    public boolean isTypedef()
    {
        return false;
    }
    public boolean isError()
    {
        return false;
    }
    public boolean isParam()
    {
        return false;
    }
    public boolean isNull()
    {
        return false;
    }
    public boolean isArrEle()
    {
        return false;
    }
    public boolean isStructdef()
    {
        return false;
    }
}
