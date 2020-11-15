package model.values;

import model.types.StringType;
import model.types.Type;

public class StringValue implements Value{

    public String value;

    public StringValue(String v){ value = v; }

    public StringValue(){

    }

    public String getValue(){
        return value;
    }

    public String toString(){
        return value;
    }

    @Override
    public boolean equals(Object another) {
        if(value == ((StringValue) another).getValue()){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Type getType() {
        return new StringType();
    }


}
