package model;

public class BoolValue implements Value{

    public boolean value;

    BoolValue(boolean v) {
        value = v;
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
