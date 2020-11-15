package model.values;

import model.types.IntType;
import model.types.Type;
import model.values.Value;

public class IntValue implements Value {

    public int value;

    public IntValue(int v) {
        value = v;
    }

    public IntValue() {

    }

    public int getValue(){
        return value;
    }

    public String toString(){
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object another) {
        if(value == ((IntValue) another).getValue()){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Type getType() {
        return new IntType();
    }

}
