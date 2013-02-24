import java.util.Vector;

public class StructdefSTO extends STO
{
    private Vector<STO> m_fieldList;
	
	public StructdefSTO(String strName) {
		super(strName, new StructType("Struct", 4));
	}

	public StructdefSTO(String id, Vector<STO> fieldList) {
		super(id, new StructType("Struct", 4));
		setFields(fieldList);
	}

	public boolean isStructdef() {
		return true;
	}
	
    Vector<STO> getFields() {
        return m_fieldList;
    }

    void setFields(Vector<STO> fields) {
        m_fieldList = fields;
    }
}
