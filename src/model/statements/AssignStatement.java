package model.statements;

import data_structures.DictionaryInterface;
import data_structures.Heap;
import data_structures.StackInterface;
import exceptions.MyException;
import model.ProgramState;
import model.expressions.Expression;
import model.types.Type;
import model.values.Value;

public class AssignStatement implements Statement{

    public String id;
    public Expression expression;

    public AssignStatement(String _id, Expression _expression){
        id = _id;
        expression = _expression;
    }

    public String toString(){
        return this.id + "=" + this.expression.toString();
    }

    @Override
    public ProgramState execute(ProgramState _state) throws MyException {
        StackInterface<Statement> stack = _state.getExecutionStack();
        DictionaryInterface<String, Value> symbolsTable = _state.getSymbolsTable();
        Heap<Integer, Value> heap = _state.getHeap();

        if(symbolsTable.isDefined(id)){
            Value val = expression.evaluate(symbolsTable, heap);
            Type typeId = (symbolsTable.lookup(id)).getType();
            if(val.getType().equals(typeId)){
                symbolsTable.update(id, val);
            }
            else{
                throw new MyException("Assignment Statement Exception: declared type of variable \'" + id + "\' and type of the assigned expression do not match");
            }
        }
        else{
            throw new MyException("Assignment Statement Exception: the used variable" + id + " was not declared before");
        }
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookup(id);
        Type typeExpr = expression.typecheck(typeEnv);
        if(typeVar.equals(typeExpr))
            return typeEnv;
        else
            throw new MyException("Assign Statement Typecheck Exception: Right hand side and left hand side have different types.");
    }

}
