package model;
import data_structures.DictionaryInterface;

public interface Expression {
    public Value evaluate (DictionaryInterface<String, Value> table) throws Exception;
}
