package data_structures;

public class HeapEntry {

    public Integer id;
    public String ref;

    public HeapEntry(Integer id, String s) {
        this.id = id;
        this.ref = s;
    }

    public Integer getId() {
        return id;
    }

    public String getRef() {
        return ref;
    }
}
