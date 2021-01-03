package model.expressions;

import data_structures.DictionaryInterface;
import data_structures.Heap;
import exceptions.MyException;
import model.types.BoolType;
import model.types.IntType;
import model.types.Type;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.Value;

public class RelationalExpression implements Expression{

    public Expression e1;
    public Expression e2;
    public String operator;

    public RelationalExpression(String _operator, Expression _e1, Expression _e2){
        e1 = _e1;
        e2 = _e2;
        operator = _operator;
    }

    public String toString(){
        return e1.toString() + operator + e2.toString();
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> symbolsTable, Heap<Integer, Value> heap) throws MyException {
        Value v1, v2;
        v1 = e1.evaluate(symbolsTable, heap);
        //checks if v1 and v2 are integers
        if(v1.getType().equals(new IntType())){
            v2 = e2.evaluate(symbolsTable, heap);
            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if(operator == "<"){
                    if(n1 < n2)
                        return new BoolValue(true);
                    else
                        return new BoolValue(false);
                }
                if(operator == "<="){
                    if(n1 <= n2)
                        return new BoolValue(true);
                    else
                        return new BoolValue(false);
                }
                if(operator == "=="){
                    if(n1 == n2)
                        return new BoolValue(true);
                    else
                        return new BoolValue(false);
                }
                if(operator == "!="){
                    if(n1 != n2)
                        return new BoolValue(true);
                    else
                        return new BoolValue(false);
                }
                if(operator == ">"){
                    if(n1 > n2)
                        return new BoolValue(true);
                    else
                        return new BoolValue(false);
                }
                if(operator == ">="){
                    if(n1 >= n2)
                        return new BoolValue(true);
                    else
                        return new BoolValue(false);
                }
            }
            else{
                throw new MyException("Relational Expression Exception: second operand is not an integer");
            }
        }
        else{
            throw new MyException("Relational Expression Exception: first operand is not an integer");
        }
        return new BoolValue(false); //should never reach here
    }

    @Override
    public Type typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1=e1.typecheck(typeEnv);
        type2=e2.typecheck(typeEnv);

        if(type1.equals(new IntType())){
            if(type2.equals(new IntType()))
                return new BoolType();
            else
                throw new MyException("Relational Expr. Typecheck Exception: Second operand is not an integer.");
        }
        else
            throw new MyException("Relational Expr. Typecheck Exception: First operand is not an integer;");
    }

}
