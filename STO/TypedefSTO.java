//---------------------------------------------------------------------
// For typedefs like: typedef int myInteger1, myInteger2;
// Also can hold the structdefs
//---------------------------------------------------------------------
import java.util.Vector;

class TypedefSTO extends STO
{

    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
/*    public TypedefSTO(String strName)
    {
        super(strName);
    }*/

    public TypedefSTO(String strName, Type typ, boolean addressable, boolean modifiable)
    {
        super(strName, typ, addressable, modifiable);
    }
    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public boolean isTypedef()
    {
        return true;
    }
}
