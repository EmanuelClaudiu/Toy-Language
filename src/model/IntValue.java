package model;

public class IntValue implements Value{

    public int value;

    IntValue(int v) {
        value = v;
    }

    public int getValue(){
        return value;
    }

    public String toString(){
        return String.valueOf(value);
    }

    @Override
    public Type getType() {
        return new IntType();
    }

}
