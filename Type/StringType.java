
public class StringType extends BasicType
{

	public StringType(String strName, int size) {
		super(strName, size);
	}
	
	public boolean isString()
	{
		return true;
	}
}
