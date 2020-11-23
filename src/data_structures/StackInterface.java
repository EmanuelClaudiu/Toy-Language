package data_structures;

import java.util.Stack;

public interface StackInterface<T> {

    public T pop();
    public void push(T _value);
    public boolean isEmpty();
    public void clear();
    public String toString();
    public String toFileString();

    public Stack<T> getContent();
    public void setContent(Stack<T> _newStack);

}
