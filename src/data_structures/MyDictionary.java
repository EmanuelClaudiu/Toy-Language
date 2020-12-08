package data_structures;
import model.values.Value;

import java.util.*;
import java.util.stream.Collectors;

public class MyDictionary<K, V> implements DictionaryInterface{

    public HashMap<K, V> dictionary = new HashMap<K, V>();

    @Override
    public void add(Object _key, Object _value) {
        dictionary.put((K) _key, (V) _value);
    }

    @Override
    public V lookup(Object _key) {
        return (V) dictionary.get(_key);
    }

    @Override
    public boolean isDefined(Object id) {
        return dictionary.containsKey((K) id);
    }

    @Override
    public void update(Object _key, Object _value) {
        dictionary.replace((K) _key,(V) _value);
    }

    @Override
    public void remove(Object _key) {
        dictionary.remove((K) _key);
    }

    @Override
    public void clear() {
        dictionary.clear();
    }

    @Override
    public String toString() {
        return dictionary.toString();
    }

    @Override
    public String toFileString() {
        String dictionaryString = "";
        for(K key : dictionary.keySet()){
            dictionaryString += (key.toString() + " --> " + dictionary.get(key).toString() + "\n");
        }
        return dictionaryString;
    }

    @Override
    public HashMap getContent() {
        return dictionary;
    }

    @Override
    public void setContent(Map _newMap) {
        dictionary = (HashMap<K, V>) _newMap;
    }

    public MyDictionary<K, V> clone(){
        MyDictionary<K, V> clone = new MyDictionary<K, V>();

        for(Map.Entry mapElement : dictionary.entrySet()){
            clone.add((K) mapElement.getKey(), (V) mapElement.getValue() );
        }

        return clone;
    }

}
