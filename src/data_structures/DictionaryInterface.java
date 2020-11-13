package data_structures;
import model.values.*;

public interface DictionaryInterface<K, V> {

    public void add(K _key, V _value);
    public V lookup(K _key);
    public boolean isDefined(K _key);
    public void update(K _key, V _value);
    public void clear();
    public String toString();
    public String toFileString();

}
