package data_structures;

import model.values.Value;

public class SymTableEntry {

    public String id;
    public Value value;

    public SymTableEntry(String id, Value s) {
        this.id = id;
        this.value = s;
    }

    public String getId() {
        return id;
    }

    public Value getValue() {
        return value;
    }

}
