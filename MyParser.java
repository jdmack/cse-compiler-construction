
//---------------------------------------------------------------------
//
//---------------------------------------------------------------------

import java_cup.runtime.*;
import java.util.Vector;

class MyParser extends parser
{

    //----------------------------------------------------------------
    //    Instance variables
    //----------------------------------------------------------------
    private Lexer        m_lexer;
    private ErrorPrinter m_errors;
    private int          m_nNumErrors;
    private String       m_strLastLexeme;
    private boolean      m_bSyntaxError = true;
    private int          m_nSavedLineNum;
    private SymbolTable  m_symtab;

    private boolean      m_inStructdef = false;
    private Scope        m_currentStructdef;

    private int          m_whileLevel;

    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    public MyParser(Lexer lexer, ErrorPrinter errors)
    {
        m_lexer = lexer;
        m_symtab = new SymbolTable();
        m_errors = errors;
        m_nNumErrors = 0;
        m_whileLevel = 0;
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    public boolean Ok()
    {
        return (m_nNumErrors == 0);
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    public Symbol scan()
    {
        Token t = m_lexer.GetToken();

        // We'll save the last token read for error messages.
        // Sometimes, the token is lost reading for the next
        // token which can be null.
        m_strLastLexeme = t.GetLexeme();

        switch(t.GetCode()) {
            case sym.T_ID:
            case sym.T_ID_U:
            case sym.T_STR_LITERAL:
            case sym.T_FLOAT_LITERAL:
            case sym.T_INT_LITERAL:
            case sym.T_CHAR_LITERAL:
                return (new Symbol(t.GetCode(), t.GetLexeme()));
            default:
                return (new Symbol(t.GetCode()));
        }
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    public void syntax_error(Symbol s)
    {
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    public void report_fatal_error(Symbol s)
    {
        m_nNumErrors++;
        if(m_bSyntaxError) {
            m_nNumErrors++;

            //    It is possible that the error was detected
            //    at the end of a line - in which case, s will
            //    be null.  Instead, we saved the last token
            //    read in to give a more meaningful error
            //    message.
            m_errors.print(Formatter.toString(ErrorMsg.syntax_error, m_strLastLexeme));
        }
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    public void unrecovered_syntax_error(Symbol s)
    {
        report_fatal_error(s);
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    public void DisableSyntaxError()
    {
        m_bSyntaxError = false;
    }

    public void EnableSyntaxError()
    {
        m_bSyntaxError = true;
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    public String GetFile()
    {
        return (m_lexer.getEPFilename());
    }

    public int GetLineNum()
    {
        return (m_lexer.getLineNumber());
    }

    public void SaveLineNum()
    {
        m_nSavedLineNum = m_lexer.getLineNumber();
    }

    public int GetSavedLineNum()
    {
        return (m_nSavedLineNum);
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    void DoProgramStart()
    {
        // Opens the global scope.
        m_symtab.openScope();
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    void DoProgramEnd()
    {
        m_symtab.closeScope();
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    void DoVarDecl(Type type, Vector<IdValueTuple> lstIDs)
    {
        for(int i = 0; i < lstIDs.size(); i++) {
            String id = lstIDs.elementAt(i).getId();
            STO value = lstIDs.elementAt(i).getValue();

            // Check for var already existing in localScope
            if(m_symtab.accessLocal(id) != null) {
                m_nNumErrors++;
                m_errors.print(Formatter.toString(ErrorMsg.redeclared_id, id));
            }

            VarSTO stoVar;

            // Do Array checks if type = ArrayType
            if(lstIDs.elementAt(i).getArrayIndex() != null && type != null) {
                // Check 11
                if(!lstIDs.elementAt(i).getArrayIndex().getType().isEquivalent(new IntType())) {
                    m_nNumErrors++;
                    m_errors.print(Formatter.toString(ErrorMsg.error10i_Array, lstIDs.elementAt(i).getArrayIndex().getType().getName()));
                    break;
                }
                if(!lstIDs.elementAt(i).getArrayIndex().isConst()) {
                    m_nNumErrors++;
                    m_errors.print(ErrorMsg.error10c_Array);
                    break;
                }
                else if(((ConstSTO)lstIDs.elementAt(i).getArrayIndex()).getIntValue() <= 0) {
                    m_nNumErrors++;
                    m_errors.print(Formatter.toString(ErrorMsg.error10z_Array,((ConstSTO)lstIDs.elementAt(i).getArrayIndex()).getIntValue()));
                    break;
                }
                ArrayType arrType;
                // Check 11b
                if(lstIDs.elementAt(i).getValue().isArrEle()) {
                    ArrEleSTO elements = (ArrEleSTO) lstIDs.elementAt(i).getValue();
                    Vector<STO> stos = elements.getArrayElements();
                    // # elements not exceed array size
                    if(stos.size() >= ((ConstSTO) lstIDs.elementAt(i).getArrayIndex()).getIntValue()) {
                        m_nNumErrors++;
                        m_errors.print(ErrorMsg.error11_TooManyInitExpr);
                        break;
                    }

                    for(STO sto : stos) {
                        if (!sto.isConst()) {
                            m_nNumErrors++;
                            m_errors.print(ErrorMsg.error11_NonConstInitExpr);
                            break;
                        }

                        if (!sto.getType().isAssignable(type)) {
                            m_nNumErrors++;
                            m_errors.print(Formatter.toString(ErrorMsg.error3b_Assign, sto.getType().getName(), type.getName()));
                            break;
                        }
                    }
                    arrType = new ArrayType(type, lstIDs.elementAt(i).getArrayIndex(), elements);
                }
                else {

                    arrType = new ArrayType(type, lstIDs.elementAt(i).getArrayIndex());
                }



                /*STO stoResult = arrType.checkArray();
                if(stoResult.isError())
                {
                    m_nNumErrors++;
                    m_errors.print(stoResult.getName());
                }*/
                stoVar = new VarSTO(id, arrType);
                m_symtab.insert(stoVar); // May be redundant but didn't want a possibility of inserting null.
            }
            else {
                stoVar = new VarSTO(id, type);
                m_symtab.insert(stoVar); // May be redundant but didn't want a possibility of inserting null.

            }

            if(!value.isNull()) {
                DoAssignExpr(stoVar, value);
            }
        }
    }

    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    void DoExternDecl(Vector<String> lstIDs)
    {
        for(int i = 0; i < lstIDs.size(); i++) {
            String id = lstIDs.elementAt(i);

            if(m_symtab.accessLocal(id) != null) {
                m_nNumErrors++;
                m_errors.print(Formatter.toString(ErrorMsg.redeclared_id, id));
            }

            VarSTO sto = new VarSTO(id);
            m_symtab.insert(sto);
        }
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    void DoConstDecl(Type type, Vector<IdValueTuple> lstIDs)
    {
        // Check for previous errors
        for(int i = 0; i < lstIDs.size(); i++) {
            if(lstIDs.elementAt(i).getValue().isError())
                return;
            //return lstIDs.elementAt(i).value;

        }

        for(int i = 0; i < lstIDs.size(); i++) {

            String id = lstIDs.elementAt(i).getId();
            STO value = lstIDs.elementAt(i).getValue();

            // Check for constant already existing in localScope
            if(m_symtab.accessLocal(id) != null) {
                m_nNumErrors++;
                m_errors.print(Formatter.toString(ErrorMsg.redeclared_id, id));
                return;
            }

            // Check #8a - init value not known at compiler time
            if(!value.isConst()) {
                m_nNumErrors++;
                m_errors.print(Formatter.toString(ErrorMsg.error8_CompileTime, id));
                return;
            }

            // Check #8b
            if(!value.getType().isAssignable(type)) {
                m_nNumErrors++;
                m_errors.print(Formatter.toString(ErrorMsg.error8_Assign, value.getType().getName(), type.getName()));
                return;
            }

            STO sto = new ConstSTO(id, type, ((ConstSTO)value).getValue());
            m_symtab.insert(sto);


        }
    }
    /*
    DoVarDecl(Type type, Vector<IdValueTuple> lstIDs)
    {
        for(int i = 0; i < lstIDs.size(); i++)
        {
            String id = lstIDs.elementAt(i).getId();
            STO value = lstIDs.elementAt(i).getValue();

            // Check for var already existing in localScope
            if(m_symtab.accessLocal(id) != null)
            {
                m_nNumErrors++;
                m_errors.print(Formatter.toString(ErrorMsg.redeclared_id, id));
            }

            VarSTO stoVar = new VarSTO(id, type);
            m_symtab.insert(stoVar);

            if(!value.isNull())
            {
                DoAssignExpr(stoVar, value);
            }

        }
    }
    */


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    void DoTypedefDecl(Type type, Vector<String> lstIDs)
    {
        for(int i = 0; i < lstIDs.size(); i++) {
            String id = lstIDs.elementAt(i);

            if(m_symtab.accessLocal(id) != null) {
                m_nNumErrors++;
                m_errors.print(Formatter.toString(ErrorMsg.redeclared_id, id));
            }
            // Do Array checks if type = ArrayType
            if(type.isArray()) {
                STO stoResult = ((ArrayType)type).checkArray();

                if(stoResult.isError()) {
                    m_nNumErrors++;
                    m_errors.print(stoResult.getName());
                }
            }

            type.setName(id);
            TypedefSTO sto = new TypedefSTO(id, type);
            m_symtab.insert(sto);
        }
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    void DoStructdefDeclStart()
    {
        m_inStructdef = true;
        m_currentStructdef = new Scope();
        // TODO: Need to add the name of the struct type to scope so we can look for the type for function pointers done inside struct
    }

    void DoStructdefField(String id, STO thisSTO)
    {
        // Check for duplicate names
        // Check 13a
        if(m_currentStructdef.accessLocal(thisSTO.getName()) != null) {
            m_nNumErrors++;
            m_errors.print(Formatter.toString(ErrorMsg.error13a_Struct, thisSTO.getName()));
        }

        // Check 13b
        else if(thisSTO.getType() != null) {
            // Check that the type is not this same type of struct and that it's not a pointer in that case
            if((thisSTO.getType().getName().equals(id)) && (!thisSTO.getType().isPointer())) {
                m_nNumErrors++;
                m_errors.print(Formatter.toString(ErrorMsg.error13b_Struct, thisSTO.getName()));
            }
        }
        else {
            m_currentStructdef.InsertLocal(thisSTO);
        }
    }


    void DoStructdefDeclFinish(String id, Vector<STO> fieldList)
    {
        // check for struct in scope
        if(m_currentStructdef.accessLocal(id) != null) {
            m_nNumErrors++;
            m_errors.print(Formatter.toString(ErrorMsg.redeclared_id, id));
        }
        else {
            StructdefSTO sto = new StructdefSTO(id, fieldList);
            m_symtab.insert(sto);
        }
        m_inStructdef = false;
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    STO DoFuncDecl_1(Type returnType, String id, Boolean retByRef)
    {
        // Check for func already existing in localScope
        if(m_symtab.accessLocal(id) != null) {
            m_nNumErrors++;
            m_errors.print(Formatter.toString(ErrorMsg.redeclared_id, id));
        }

        FuncSTO sto = new FuncSTO(id, retByRef);
        // Set return type
        sto.setReturnType(returnType);

        m_symtab.insert(sto);

        m_symtab.openScope();
        m_symtab.setFunc(sto);

        // Set the function's level
        sto.setLevel(m_symtab.getLevel());

        return sto;
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    void DoFuncDecl_2()
    {
        FuncSTO stoFunc;

        if((stoFunc = m_symtab.getFunc()) == null) {
            m_nNumErrors++;
            m_errors.print("internal: DoFuncDecl_2 says no proc!");
            return;
        }

        // Check #6c - no return statement for non-void type function
        if(!stoFunc.getReturnType().isVoid()) {
            if(!stoFunc.getHasReturnStatement()) {
                m_nNumErrors++;
                m_errors.print(ErrorMsg.error6c_Return_missing);
            }

        }

        m_symtab.closeScope();
        m_symtab.setFunc(null);
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    void DoFormalParams(Vector<ParamSTO> params)
    {
        FuncSTO stoFunc;

        if((stoFunc = m_symtab.getFunc()) == null) {
            m_nNumErrors++;
            m_errors.print("internal: DoFormalParams says no proc!");
            return;
        }

        // Insert parameters
        stoFunc.setParameters(params);

        // Add parameters to local scope
        for(STO thisParam: params) {
            /* Not sure if want this check
            if(m_symtab.accessLocal(id) != null)
            {
                m_nNumErrors++;
                m_errors.print(Formatter.toString(ErrorMsg.redeclared_id, id));
            }
            */
            m_symtab.insert(thisParam);
        }
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    void DoBlockOpen()
    {
        // Open a scope.
        m_symtab.openScope();
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    void DoBlockClose()
    {
        m_symtab.closeScope();
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    STO DoAssignExpr(STO stoDes, STO stoValue)
    {
        // Check for previous errors in line and short circuit
        if(stoDes.isError()) {
            return stoDes;
        }
        if(stoValue.isError()) {
            return stoValue;
        }

        // Check #3a - illegal assignment - not modifiable L-value
        if(!stoDes.isModLValue()) {
            m_nNumErrors++;
            m_errors.print(ErrorMsg.error3a_Assign);
            return (new ErrorSTO("DoAssignExpr Error - not mod-L-Value"));
        }

        if(!stoValue.getType().isAssignable(stoDes.getType())) {
            m_nNumErrors++;
            m_errors.print(Formatter.toString(ErrorMsg.error3b_Assign, stoValue.getType().getName(), stoDes.getType().getName()));
            return (new ErrorSTO("DoAssignExpr Error - bad types"));
        }

        return stoDes;
    }

    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    STO DoFuncCall(STO sto, Vector<STO> args)
    {
        // Check for previous errors
        if(sto.isError())
            return sto;

        for(int i = 0; i < args.size(); i++) {
            if(args.elementAt(i).isError())
                return args.elementAt(i);
        }

        if(!sto.isFunc()) {
            m_nNumErrors++;
            m_errors.print(Formatter.toString(ErrorMsg.not_function, sto.getName()));
            return (new ErrorSTO(sto.getName()));
        }

        // We know it's a function, do function call checks
        FuncSTO stoFunc =(FuncSTO)sto;

        // Check #5
        // Check #5a - # args = # params
        if((stoFunc.getNumOfParams() != args.size())) {
            m_nNumErrors++;
            m_errors.print(Formatter.toString(ErrorMsg.error5n_Call, args.size(), stoFunc.getNumOfParams()));
            return (new ErrorSTO("DoFuncCall - # args"));
        }

        // Now we check each arg individually, accepting one error per arg

        boolean error_flag = false;

        for(int i = 0; i < args.size(); i++) {
            // For readability and shorter lines
            ParamSTO thisParam = stoFunc.getParameters().elementAt(i);
            STO thisArg = args.elementAt(i);

            // Check #5b - non-assignable arg for pass-by-value param
            if(!thisParam.isPassByReference()) {
                if(!thisArg.getType().isAssignable(thisParam.getType())) {
                    m_nNumErrors++;
                    m_errors.print(Formatter.toString(ErrorMsg.error5a_Call, thisArg.getType().getName(), thisParam.getName(), thisParam.getType().getName()));
                    error_flag = true;
                    continue;
                }
            }
            else {
                // Check #5c - arg type not equivalent to pass-by-ref param type
                if(!thisArg.getType().isEquivalent(thisParam.getType())) {
                    m_nNumErrors++;
                    m_errors.print(Formatter.toString(ErrorMsg.error5r_Call, thisArg.getType().getName(), thisParam.getName(), thisParam.getType().getName()));
                    error_flag = true;
                    continue;
                }

                // Check #5d - arg not modifiable l-value for pass by ref param
                if(!thisArg.isModLValue()) {
                    m_nNumErrors++;
                    m_errors.print(Formatter.toString(ErrorMsg.error5c_Call, thisParam.getName(), thisArg.getType().getName()));
                    error_flag = true;
                    continue;
                }
            }
        }

        if(error_flag) {
            // Error occured in at least one arg, return error
            return (new ErrorSTO("DoFuncCall - Check 5"));
        }
        else {
            // Func call legal, return function return type
            return (new ExprSTO(stoFunc.getName() + " return type", stoFunc.getReturnType()));
        }
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    STO DoDesignator2_Dot(STO sto, String strID)
    {
        // Good place to do the struct checks
        if(sto.isError()) {
            return sto;
        }

        // Check #14a
        // type of struct is not a struct type
        if(!sto.getType().isStruct()) {
            m_nNumErrors++;
            m_errors.print(Formatter.toString(ErrorMsg.error14t_StructExp, sto.getType().getName()));
            return new ErrorSTO("Struct Error - not a struct");
        }

        // Check #14b
        if (m_inStructdef) {
            if(m_currentStructdef.accessLocal(strID) == null) {
                m_nNumErrors++;
                m_errors.print(Formatter.toString(ErrorMsg.error14b_StructExpThis, strID));
                return new ErrorSTO("Struct Error - field not in Struct");
            }
        }
        else {
            // Check #14a
            boolean found_flag = false;        // type of struct does not contain the field or function
            Vector<STO> fieldList = ((StructdefSTO)sto).getFields();

            for(STO thisSTO: fieldList) {
                if(thisSTO.getName().equals(strID)) {
                    found_flag = true;
                }
            }

            if(!found_flag) {
                m_nNumErrors++;
                m_errors.print(Formatter.toString(ErrorMsg.error14f_StructExp, strID, sto.getType().getName()));
                return new ErrorSTO("Struct Error - field not found in type");
            }
        }


        return sto;
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    STO DoDesignator2_Array(STO desSTO, STO indexSTO)
    {
        // desSTO: the identifier
        // indexSTO: the expression inside the []


        // Check #11a
        // bullet 1 - desSTO is not array or pointer type
        if((!desSTO.getType().isArray()) && (!desSTO.getType().isPointer())) {
            m_nNumErrors++;
            m_errors.print(Formatter.toString(ErrorMsg.error11t_ArrExp, desSTO.getType().getName()));
            return new ErrorSTO("Desig2_Array() - Not array or ptr");
        }

        // bullet 2 - index expression type is not equiv to int
        if(!indexSTO.getType().isEquivalent(new IntType())) {
            m_nNumErrors++;
            m_errors.print(Formatter.toString(ErrorMsg.error11i_ArrExp, indexSTO.getType().getName()));
            return new ErrorSTO("Desig2_Array() - index not equiv to int");
        }

        // bullet 3 - index expr is constant, error if indexExpr outside bounds of array dimension
        //              except when desSTO is pointer type
        if(indexSTO.isConst()) {
            if(((ConstSTO)indexSTO).getIntValue() >= ((ConstSTO)((ArrayType)desSTO.getType()).getDimensionSize()).getIntValue() || ((ConstSTO)indexSTO).getIntValue() < 0) {
                m_nNumErrors++;
                m_errors.print(Formatter.toString(ErrorMsg.error11b_ArrExp, ((ConstSTO)indexSTO).getIntValue(), ((ConstSTO)((ArrayType)desSTO.getType()).getDimensionSize()).getIntValue()));
                return new ErrorSTO("Desig2_Array() - index is constant, out of bounds");
            }
        }

        // Checks are complete, now we need to return an ExprSTO with the type of the array elements
        desSTO = new VarSTO(((ArrayType)desSTO.getType()).getElementType().getName(),((ArrayType)desSTO.getType()).getElementType());
        //TODO: Double check what the name of exprSTO should be.
        return desSTO;
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    STO DoDesignator3_GlobalID(String strID)
    {
        STO sto;

        if((sto = m_symtab.accessGlobal(strID)) == null) {
            m_nNumErrors++;
            m_errors.print(Formatter.toString(ErrorMsg.error0g_Scope, strID));
            sto = new ErrorSTO(strID);
        }
        return (sto);
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    STO DoDesignator3_ID(String strID)
    {
        STO sto;

        if((sto = m_symtab.access(strID)) == null) {
            m_nNumErrors++;
            m_errors.print(Formatter.toString(ErrorMsg.undeclared_id, strID));
            sto = new ErrorSTO(strID);
        }
        return (sto);
    }


    //----------------------------------------------------------------
    //
    //----------------------------------------------------------------
    STO DoQualIdent(String strID)
    {
        STO sto;
        if((sto = m_symtab.access(strID)) == null) {
            m_nNumErrors++;
            m_errors.print(Formatter.toString(ErrorMsg.undeclared_id, strID));
            return (new ErrorSTO(strID));
        }
        if(!sto.isTypedef()) {
            m_nNumErrors++;
            m_errors.print(Formatter.toString(ErrorMsg.not_type, sto.getName()));
            return (new ErrorSTO(sto.getName()));
        }

        return (sto);
    }

    //----------------------------------------------------------------
    //      DoBinaryOp
    //----------------------------------------------------------------
    STO DoBinaryOp(BinaryOp op, STO operand1, STO operand2)
    {
        // Check for previous errors in line and short circuit
        if(operand1.isError()) {
            return operand1;
        }
        if(operand2.isError()) {
            return operand2;
        }

        // Use BinaryOp.checkOperands() to perform error checks
        STO resultSTO = op.checkOperands(operand1, operand2);

        // If operands are constants, do the op
        if((!resultSTO.isError()) && (resultSTO.isConst())) {
            resultSTO =  op.doOperation((ConstSTO)operand1, (ConstSTO)operand2, resultSTO.getType());
        }

        // Process/Print errors
        if(resultSTO.isError()) {
            m_nNumErrors++;
            m_errors.print(resultSTO.getName());
        }

        return resultSTO;
    }

    //----------------------------------------------------------------
    //      DoUnaryOp
    //----------------------------------------------------------------
    STO DoUnaryOp(UnaryOp op, STO operand)
    {
        // Check for previous errors in line and short circuit
        if(operand.isError()) {
            return operand;
        }

        // Use UnaryOp.checkOperand() to perform error checks
        STO resultSTO = op.checkOperand(operand);

        // If operand is a constant, do the op
        if((!resultSTO.isError()) && (resultSTO.isConst())) {
            resultSTO =  op.doOperation((ConstSTO)operand, resultSTO.getType());
        }

        // Process/Print errors
        if(resultSTO.isError()) {
            m_nNumErrors++;
            m_errors.print(resultSTO.getName());
        }

        return resultSTO;
    }

    //----------------------------------------------------------------
    //      DoWhileExpr
    //----------------------------------------------------------------
    STO DoWhileExpr(STO stoExpr)
    {
        // Check for previous errors in line and short circuit
        if(stoExpr.isError()) {
            return stoExpr;
        }

        // Check #4 - while expr - int or bool
        if((!stoExpr.getType().isInt()) &&(!stoExpr.getType().isBool())) {
            m_nNumErrors++;
            m_errors.print(Formatter.toString(ErrorMsg.error4_Test, stoExpr.getType().getName()));
            return (new ErrorSTO("DoWhile error"));
        }

        whileLevelUp();

        return stoExpr;
    }

    //----------------------------------------------------------------
    //      DoIfExpr
    //----------------------------------------------------------------
    STO DoIfExpr(STO stoExpr)
    {
        // Check for previous errors in line and short circuit
        if(stoExpr.isError()) {
            return stoExpr;
        }

        // Check #4 - if expr - int or bool
        if((!stoExpr.getType().isInt()) &&(!stoExpr.getType().isBool())) {
            m_nNumErrors++;
            m_errors.print(Formatter.toString(ErrorMsg.error4_Test, stoExpr.getType().getName()));
            return (new ErrorSTO("DoIf error"));
        }

        return stoExpr;
    }

    //----------------------------------------------------------------
    //      DoReturnStmt_1
    //----------------------------------------------------------------
    STO DoReturnStmt_1()
    {
        FuncSTO stoFunc;

        if((stoFunc = m_symtab.getFunc()) == null) {
            m_nNumErrors++;
            m_errors.print("internal: DoReturnStmt_1 says no proc!");
            return (new ErrorSTO("DoReturnStmt_1 Error"));
        }

        // Check #6a - no expr on non-void rtn
        if(!stoFunc.getReturnType().isVoid()) {
            m_nNumErrors++;
            m_errors.print(ErrorMsg.error6a_Return_expr);
            return (new ErrorSTO("DoReturnStmt_1 Error"));
        }

        // valid return statement, set func.hasReturnStatement if at right level
        if(stoFunc.getLevel() == m_symtab.getLevel()) {
            stoFunc.setHasReturnStatement(true);
        }

        return (new ExprSTO(stoFunc.getName() + " Return", new VoidType()));

    }

    //----------------------------------------------------------------
    //      DoReturnStmt_2
    //----------------------------------------------------------------
    STO DoReturnStmt_2(STO stoExpr)
    {
        FuncSTO stoFunc;

        // Check for previous errors in line and short circuit
        if(stoExpr.isError()) {
            return stoExpr;
        }

        if((stoFunc = m_symtab.getFunc()) == null) {
            m_nNumErrors++;
            m_errors.print("internal: DoReturnStmt_2 says no proc!");
            return (new ErrorSTO("DoReturnStmt_2 Error"));
        }

        // Check #6b - 1st bullet - rtn by val - rtn expr type not assignable to return
        if(!stoFunc.getReturnByRef()) {
            if(!stoExpr.getType().isAssignable(stoFunc.getReturnType())) {
                m_nNumErrors++;
                m_errors.print(Formatter.toString(ErrorMsg. error6a_Return_type, stoExpr.getType().getName(), stoFunc.getReturnType().getName()));
                return (new ErrorSTO("DoReturnStmt_2 Error"));
            }
        }
        else {
            // Check #6b - 2nd bullet - rtn by ref - rtn expr type not equivalent to return type
            if(!stoExpr.getType().isEquivalent(stoFunc.getReturnType())) {
                m_nNumErrors++;
                m_errors.print(Formatter.toString(ErrorMsg.error6b_Return_equiv, stoExpr.getType().getName(), stoFunc.getReturnType().getName()));
                return (new ErrorSTO("DoReturnStmt_2 Error"));
            }

            // Check #6b - 3rd bullet - rtn by ref - rtn expr not modLValue
            if(!stoExpr.isModLValue()) {
                m_nNumErrors++;
                m_errors.print(ErrorMsg.error6b_Return_modlval);
                return (new ErrorSTO("DoReturnStmt_2 Error"));
            }
        }

        // valid return statement, set func.hasReturnStatement if at right level
        if(stoFunc.getLevel() == m_symtab.getLevel()) {
            stoFunc.setHasReturnStatement(true);
        }

        return stoExpr;
    }

    //----------------------------------------------------------------
    //      DoExitStmt
    //----------------------------------------------------------------
    STO DoExitStmt(STO stoExpr)
    {
        // Check for previous errors in line and short circuit
        if(stoExpr.isError()) {
            return stoExpr;
        }

        // Check #7 - exit value assignable to int
        if(!stoExpr.getType().isAssignable(new IntType())) {
            m_nNumErrors++;
            m_errors.print(Formatter.toString(ErrorMsg.error7_Exit, stoExpr.getType().getName()));
        }

        return stoExpr;
    }

    //----------------------------------------------------------------
    //      DoBreakStmt
    //----------------------------------------------------------------
    void DoBreakStmt()
    {
        // Check #12 - break statement in while loop
        if(m_whileLevel <= 0) {
            m_nNumErrors++;
            m_errors.print(ErrorMsg.error12_Break);
        }
    }

    //----------------------------------------------------------------
    //      DoBreakStmt
    //----------------------------------------------------------------
    void DoContinueStmt()
    {
        // Check #12 - continue statement in while loop
        if(m_whileLevel <= 0) {
            m_nNumErrors++;
            m_errors.print(ErrorMsg.error12_Continue);
        }
    }

    void whileLevelUp()
    {
        m_whileLevel++;
    }

    void whileLevelDown()
    {
        m_whileLevel--;
    }
}
