package model.expressions;

import data_structures.DictionaryInterface;
import data_structures.Heap;
import exceptions.MyException;
import model.types.*;
import model.values.*;

public class ArithmeticExpression implements Expression{

    public Expression e1;
    public Expression e2;
    public int operator; // 1-plus, 2-minus, 3-multiplication, 4-divide

    public ArithmeticExpression(int _operator, Expression _e1, Expression _e2){
        e1 = _e1;
        e2 = _e2;
        if(_operator == 42){ // *
            operator = 3;
        }
        if(_operator == 43){ // +
            operator = 1;
        }
        if(_operator == 45){ // -
            operator = 2;
        }
        if(_operator == 47){ // /
            operator = 4;
        }
    }

    public String toString(){
        if(operator == 3){ // *
            return e1.toString() + "*" + e2.toString();
        }
        if(operator == 1){ // +
            return e1.toString() + "+" + e2.toString();
        }
        if(operator == 2){ // -
            return e1.toString() + "-" + e2.toString();
        }
        if(operator == 4){ // /
            return e1.toString() + "/" + e2.toString();
        }
        return "null"; //should never reach here
    }

    @Override
    public Value evaluate(DictionaryInterface<String, Value> symbolsTable, Heap <Integer, Value> heap) throws MyException{
        Value v1, v2;
        v1 = e1.evaluate(symbolsTable, heap);
        // checks if v1 and v2 are integers
        if(v1.getType().equals(new IntType())){
            v2 = e2.evaluate(symbolsTable, heap);
            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if(operator == 1) return new IntValue(n1 + n2); // addition
                if(operator == 2) return new IntValue(n1 - n2); // substraction
                if(operator == 3) return new IntValue(n1 * n2); // multiplication
                if(operator == 4){
                    if(n2 == 0) throw new MyException("Arithmetic Expression Exception: division by zero not allowed");
                    return new IntValue(n1 / n2);
                }
                else {
                    throw new MyException("Arithmetic Expression Exception: second operand is not an integer");
                }
            }
        }
        else {
            throw new MyException("Arithmetic Expression Exception: first operand is not integer");
        }

        return new IntValue(-1); // should never reach this point
    }

    @Override
    public Type typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1=e1.typecheck(typeEnv);
        type2=e2.typecheck(typeEnv);

        if(type1.equals(new IntType())){
            if(type2.equals(new IntType()))
                return new IntType();
            else
                throw new MyException("Arith. Expr. Typecheck Exception: Second operand is not an integer.");
        }
        else
            throw new MyException("Arith. Expr. Typecheck Exception: First operand is not an integer.");
    }

}
