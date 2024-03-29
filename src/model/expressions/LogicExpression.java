package model.expressions;

import data_structures.DictionaryInterface;
import data_structures.Heap;
import exceptions.MyException;
import model.types.*;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.Value;

public class LogicExpression implements Expression{

    public Expression e1;
    public Expression e2;
    public int operator; // 1-&& (and), 2-|| (or)

    public LogicExpression(Expression _e1, Expression _e2, int _operator){
        e1 = _e1;
        e2 = _e2;
        operator = _operator;
    }

    public String toString(){
        if(operator == 1){
            return e1.toString() + "&&" + e2.toString();
        }
        if(operator == 2){
            return e1.toString() + "||" + e2.toString();
        }
        return "null"; //should never reach here
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table, Heap<Integer, Value> heap) throws MyException {
        Value v1, v2;
        v1 = e1.evaluate(table, heap);
        //checks if v1 and v2 are booleans
        if(v1.getType().equals(new BoolType())){
            v2 = e2.evaluate(table, heap);
            if(v2.getType().equals(new BoolType())){
                BoolValue i1 = (BoolValue) v1;
                BoolValue i2 = (BoolValue) v2;
                boolean n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if(operator == 1) return new BoolValue(n1 && n2); // and
                if(operator == 2) return new BoolValue(n1 || n2); // or
            }
            else{
                throw new MyException("Logic Expression Exception: second operand is not boolean");
            }
        }
        else {
            throw new MyException("Logic Expression Exception: first operand is not boolean");
        }

        return new BoolValue(true); // should never reach this point
    }

    @Override
    public Type typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1=e1.typecheck(typeEnv);
        type2=e2.typecheck(typeEnv);

        if(type1.equals(new BoolType())){
            if(type2.equals(new BoolType()))
                return new BoolType();
            else
                throw new MyException("Logic Expr. Typecheck Exception: Second operand is not a boolean.");
        }
        else
            throw new MyException("Logic Expr. Typecheck Exception: First operand is not a boolean.");
    }

}
