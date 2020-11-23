package data_structures;

import java.util.HashMap;
import java.util.Map;

public class Heap<K, V> implements DictionaryInterface {

    public HashMap<K, V> dictionary = new HashMap<K, V>();

    @Override
    public void add(Object _key, Object _value) {
        dictionary.put((K) _key, (V) _value);
    }

    public int addNewEntry(Object _value){
        //generating an available spot in the heap
        int newEntry = -1;
        int i = 1;
        while(newEntry == -1){
            if(dictionary.containsKey(i)){
                i += 1;
            }
            else{
                newEntry = i;
            }
        }

        //putting a new entry in the heap
        dictionary.put((K) (Object) newEntry, (V) _value);

        return newEntry;
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

    @Override
    public void remove(Object _key) {
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
        dictionary = (HashMap<K,V>) _newMap;
    }

}
