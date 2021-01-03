package model.statements;

import data_structures.ArrayInterface;
import data_structures.DictionaryInterface;
import data_structures.Heap;
import exceptions.MyException;
import model.ProgramState;
import model.expressions.Expression;
import model.types.Type;
import model.values.Value;

import java.util.ArrayList;

public class PrintStatement  implements Statement{

    public Expression expression;

    public PrintStatement(Expression _expression){
        expression = _expression;
    }

    public String toString(){
        return "print(" + this.expression.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState _state) throws MyException {
        ArrayInterface<Value> output = _state.getOutput();

        output.add(expression.evaluate(_state.getSymbolsTable(), _state.getHeap()));
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        expression.typecheck(typeEnv);
        return typeEnv;
    }
}
