//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

class ArrowOp extends UnaryOp
{
    //---------------------------------------------------------------------
    //      Constructors
    //---------------------------------------------------------------------
    public ArrowOp(String strName)
    {
        super(strName);
    }

    //---------------------------------------------------------------------
    //      Methods
    //---------------------------------------------------------------------
    public STO checkOperand(STO operand)
    {
        STO resultSTO;

        // Check #15a - operand is pointer type
        // Check operand
        if(operand.getType().isPtrGrp()) {
            if(!((PtrGrpType) (operand.getType())).getBottomPtrType().isStruct())
                return(new ErrorSTO(Formatter.toString(ErrorMsg.error15_ReceiverArrow, operand.getType().getName())));
        }
        else {
            return(new ErrorSTO(Formatter.toString(ErrorMsg.error15_ReceiverArrow, operand.getType().getName())));
        }

        /* Don't need to worry about constant folding because constant pointers WNBT */

        resultSTO = new VarSTO("ArrowOp.checkOperands() Result", ((PtrGrpType)operand.getType()).getPointsToType());

        return resultSTO;
    }
}
