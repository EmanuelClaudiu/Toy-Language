package model.expressions;

import data_structures.DictionaryInterface;
import data_structures.Heap;
import exceptions.MyException;
import model.types.RefType;
import model.types.Type;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.RefValue;
import model.values.Value;

public class ReadHeapExpression implements Expression {

    public Expression expression;

    public ReadHeapExpression(Expression _expression){
        expression = _expression;
    }

    public String toString(){
        return "rH(" + expression.toString() + ")";
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table, Heap<Integer, Value> heap) throws MyException {
        Value e = expression.evaluate(table, heap);
        if(e instanceof RefValue){
            Integer address = ( (RefValue) e).getAddress();
            if(heap.isDefined(address)){
                Value associatedValue = (Value) heap.lookup(address);
                return associatedValue;
            }
            else{
                throw new MyException("Read Heap Expression Exception: Address after evaluation does not exist in the heap.");
            }
        }
        else{
            throw new MyException("Read Heap Expression Exception: Expression evaluation is not a Ref Value.");
        }
    }

    @Override
    public Type typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        Type type = expression.typecheck(typeEnv);

        if(type instanceof RefType) {
            RefType refType =(RefType) type;
            return refType.getInner();
        }
        else
            throw new MyException("rH Expr. Typecheck Exception: The rH argument is not a Ref Type.");
    }

}
