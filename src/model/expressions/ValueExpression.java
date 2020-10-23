package model.expressions;

import data_structures.DictionaryInterface;
import exceptions.MyException;
import model.values.Value;

public class ValueExpression implements Expression{

    public Value e;

    public ValueExpression(Value _e){
        e = _e;
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table) throws MyException {
        return null;
    }

}
