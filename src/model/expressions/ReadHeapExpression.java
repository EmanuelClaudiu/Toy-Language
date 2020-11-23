package model.expressions;

import data_structures.DictionaryInterface;
import data_structures.Heap;
import exceptions.MyException;
import model.values.RefValue;
import model.values.Value;

public class ReadHeapExpression implements Expression {

    public Expression expression;

    public ReadHeapExpression(Expression _expression){
        expression = _expression;
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table, Heap<Integer, Value> heap) throws MyException {
        Value e = expression.evaluate(table, heap);
        if(e instanceof RefValue){

        }
        else{
            throw new MyException("Read Heap Expression Exception: Expression evaluation is not a Ref Value.");
        }

        return null;
    }

}
