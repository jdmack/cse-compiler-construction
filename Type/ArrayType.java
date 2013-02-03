//---------------------------------------------------------------------
// 
//---------------------------------------------------------------------

class ArrayType extends CompositeType
{
	Type elementType;
	int dimensionSize;
	
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public 
    ArrayType(String strName, int size)
    {
        super(strName, size);
    }
    
    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public boolean isArray() { return true; }
    
    public Type getElementType() {
    	return elementType;
    }
    
    public void setElementType(Type type) {
    	elementType = type;
    }
    
    public int getDimensionSize() {
    	return dimensionSize;
    }
    
    public void setDimensionSize(int size) {
    	dimensionSize = size;
    }
}
