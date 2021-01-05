package data_structures;

public class HeapEntry {

    public String id;
    public String ref;

    public HeapEntry(String id, String s) {
        this.id = id;
        this.ref = s;
    }

    public String getId() {
        return id;
    }

    public String getRef() {
        return ref;
    }
}
