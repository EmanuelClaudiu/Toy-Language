package model.types;

import model.values.IntValue;
import model.values.Value;

public class IntType implements Type {

    public boolean equals(Object another){
        if (another instanceof IntType)
            return true;
        else
            return false;
    }

    public String toString() {
        return "int";
    }

    @Override
    public Value defaultValue() {
        return new IntValue(0);
    }

}
