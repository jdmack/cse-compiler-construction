//---------------------------------------------------------------------
// For typedefs like: typedef int myInteger1, myInteger2;
// Also can hold the structdefs
//---------------------------------------------------------------------
import java.util.Vector;

class TypedefSTO extends STO
{
    private Vector<STO> m_fieldList;

    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    TypedefSTO(String strName)
    {
        super(strName);
    }

    public 
    TypedefSTO(String strName, Type typ)
    {
        super(strName, typ);
    }

    public 
    TypedefSTO(String strName, Vector<STO> fields) 
    {
        super(strName, new VoidType());
        setFields(fields);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public boolean
    isTypedef()
    {
        return true;
    }

    Vector<STO>
    getFields()
    {
        return m_fieldList;
    }

    void
    setFields(Vector<STO> fields)
    {
        m_fieldList = fields;
    }
}
