package data_structures;

public interface StackInterface<T> {

    public T pop();
    public void push(T _value);
    public boolean isEmpty();
    public void clear();
    public String toString();
    public String toFileString();

}
