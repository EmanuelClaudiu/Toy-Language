package model.values;

import model.types.Type;

public interface Value {
    public boolean equals(Object another);
    public Type getType();
}
