package data_structures;

import java.util.HashMap;

public class FileTable<K, V> implements DictionaryInterface{

    public HashMap<K, V> dictionary = new HashMap<K, V>();

    @Override
    public void add(Object _key, Object _value) {
        dictionary.put((K) _key, (V) _value);
    }

    @Override
    public Object lookup(Object _key) {
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

    public void remove(Object _key){
        dictionary.remove((K) _key);
    }

    @Override
    public void clear() {
        dictionary.clear();
    }

    public String toString(){
        return dictionary.toString();
    }

    @Override
    public String toFileString() {
        String dictionaryString = "";
        for(K key : dictionary.keySet()){
            dictionaryString += ( key.toString() + "\n");
        }
        return dictionaryString;
    }

}
