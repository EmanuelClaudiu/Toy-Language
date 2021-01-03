package model.expressions;

import data_structures.DictionaryInterface;
import data_structures.Heap;
import exceptions.MyException;
import model.types.Type;
import model.values.Value;

public class VariableExpression implements Expression{

    public String id;

    public VariableExpression(String _id){
        id = _id;
    }

    public String toString(){
        return id.toString();
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table, Heap <Integer, Value> heap) throws MyException {
        return table.lookup(id);
    }

    @Override
    public Type typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        return typeEnv.lookup(id);
    }

}
