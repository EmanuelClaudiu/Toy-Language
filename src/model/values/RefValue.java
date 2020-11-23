package model.values;

import model.types.RefType;
import model.types.Type;

public class RefValue implements Value {

    public Integer address;
    public Type locationType;

    public RefValue(Integer _address, Type _locationType){
        address = _address;
        locationType = _locationType;
    }

    public String toString(){
        return "(" + address.toString() + "," + locationType.toString() + ")";
    }

    public Integer getAddress(){
        return address;
    }

    public Type getLocationType(){
        return locationType;
    }

    @Override
    public Type getType() {
        return new RefType(locationType);
    }

}
