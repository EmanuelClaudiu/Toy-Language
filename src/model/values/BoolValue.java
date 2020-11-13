package model.values;

import model.types.BoolType;
import model.types.Type;
import model.values.Value;

public class BoolValue implements Value {

    public boolean value;

    public BoolValue(boolean v) {
        value = v;
    }

    public BoolValue() {

    }

    public boolean getValue(){
        return value;
    }

    public String toString(){
        if (value)
            return "true";
        else
            return "false";
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

}