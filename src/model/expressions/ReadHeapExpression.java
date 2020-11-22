package model.expressions;

import data_structures.DictionaryInterface;
import exceptions.MyException;
import model.values.RefValue;
import model.values.Value;

public class ReadHeapExpression implements Expression {

    public Expression expression;

    public ReadHeapExpression(Expression _expression){
        expression = _expression;
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table) throws MyException {
        Value e = expression.evaluate(table);
        if(e instanceof RefValue){

        }
        else{
            throw new MyException("Read Heap Expression Exception: Expression evaluation is not a Ref Value.");
        }

        return null;
    }

}
