package model.expressions;
import data_structures.DictionaryInterface;
import exceptions.MyException;
import model.values.Value;

public interface Expression {
    public Value evaluate (DictionaryInterface<String, Value> table) throws MyException;
}
