package data_structures;
import model.values.*;

public interface DictionaryInterface<K, V> {

    public Value lookup(K _key);

}
