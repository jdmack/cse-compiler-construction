//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class AddressOfOp extends UnaryOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public AddressOfOp(String strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO checkOperand(STO operand)
    {
        STO resultSTO;

        // Check #21
        // Check operand is addressable
        if(!operand.getIsAddressable()) {
            return(new ErrorSTO(Formatter.toString(ErrorMsg.error21_AddressOf, operand.getType().getName())));
        }

        /* Don't need to worry about constant folding */

        resultSTO = new ExprSTO("&" + operand.getName() " result", new IntType());


        return resultSTO;
    }

    public boolean isAddressOfOp()
    {
        return true;
    }
}
