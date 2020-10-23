package data_structures;
import model.values.Value;

import java.util.*;

public class MyDictionary<K, V> implements DictionaryInterface{

    public Dictionary<String, Value> dictionary;

    @Override
    public Value lookup(Object _key) {
        return (Value) dictionary.get(_key);
    }

}
