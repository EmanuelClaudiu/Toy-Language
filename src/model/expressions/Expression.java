package model.expressions;
import data_structures.DictionaryInterface;
import data_structures.Heap;
import exceptions.MyException;
import model.values.Value;

public interface Expression {
    public Value evaluate (DictionaryInterface<String, Value> table, Heap <Integer, Value> heap) throws MyException;
}
