package model.expressions;

import data_structures.DictionaryInterface;
import data_structures.Heap;
import exceptions.MyException;
import model.values.Value;

public class ValueExpression implements Expression{

    public Value e;

    public ValueExpression(Value _e){
        e = _e;
    }

    public String toString(){
        return e.toString();
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table, Heap <Integer, Value> heap) throws MyException {
        return e;
    }

}
