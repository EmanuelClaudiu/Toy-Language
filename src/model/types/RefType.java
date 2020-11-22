package model.types;

import model.values.RefValue;
import model.values.Value;

public class RefType implements Type {

    public Type inner;

    public RefType(Type _inner){
        inner = _inner;
    }

    public Type getInner(){
        return inner;
    }

    public boolean equals(Object another){
        if(another instanceof RefType){
            //this might need a modification
            return inner.equals(((RefType) another).getInner());
        }
        else{
            return false;
        }
    }

    public String toString(){
        return "Ref(" + inner.toString() + ")";
    }

    @Override
    public Value defaultValue() {
        return new RefValue(0, inner);
    }

}
