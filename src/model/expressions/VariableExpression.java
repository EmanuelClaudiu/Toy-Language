package model.expressions;

import data_structures.DictionaryInterface;
import exceptions.MyException;
import model.values.Value;

public class VariableExpression implements Expression{

    public String id;

    public VariableExpression(String _id){
        id = _id;
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table) throws MyException {
        return table.lookup(id);
    }

}
