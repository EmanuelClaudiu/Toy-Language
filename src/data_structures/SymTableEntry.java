package data_structures;

import model.values.Value;

public class SymTableEntry {

    public String id;
    public String value;

    public SymTableEntry(String id, String s) {
        this.id = id;
        this.value = s;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

}
