//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

import java.util.Vector;

class ArrayType extends CompositeType
{
    Type elementType;
    Integer dimensionSize;
    Vector<STO> elementList;

    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public ArrayType(Type elementType, Integer size)
    {
        this(elementType, size, new Vector<STO>());
    }

    public ArrayType(Type elementType, Integer size, Vector<STO> eleList)
    {
        super(elementType.getName()+"[" + size + "]", elementType.getSize() * size);
        setElementType(elementType);
        setDimensionSize(size);
        setElementList(eleList);

    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public boolean isArray()
    {
        return true;
    }

    public Type getElementType()
    {
        return elementType;
    }

    public void setElementType(Type type)
    {
        elementType = type;
    }

    public Integer getDimensionSize()
    {
        return dimensionSize;
    }

    public void setDimensionSize(Integer dimenionSize)
    {
        dimensionSize = dimenionSize;
    }

    public Vector<STO> getElementList()
    {
        return elementList;
    }

    public void setElementList(Vector<STO> eleList)
    {
        elementList = eleList;
    }

    // meaning: param = this
    public boolean isAssignable(Type type)
    {
        // If trying to assign to pointer, check if pointer type is this array's element type
        if(type.isPointer()) {
            if(elementType.isAssignable(((PointerType) type).getPointsToType()))
                return true;
            else
                return false;
        }

        if(isEquivalent(type)) {
            return true;
        }

        return false;
    }

}
