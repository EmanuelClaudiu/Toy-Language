package data_structures;
import model.values.*;

import java.util.HashMap;
import java.util.Map;

public interface DictionaryInterface<K, V> {

    public void add(K _key, V _value);
    public V lookup(K _key);
    public boolean isDefined(K _key);
    public void update(K _key, V _value);
    public void remove(K _key);
    public void clear();
    public String toString();
    public String toFileString();

    public HashMap<K,V> getContent();
    public void setContent(Map _newMap);

}
