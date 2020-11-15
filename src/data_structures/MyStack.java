package data_structures;
import java.util.*;

public class MyStack<T> implements StackInterface {

    public Stack<T> myStack = new Stack<T>();

    @Override
    public T pop() {
        return (T) myStack.pop();
    }

    @Override
    public void push(Object _object) {
        myStack.push((T) _object);
    }

    @Override
    public boolean isEmpty() {
        return myStack.isEmpty();
    }

    @Override
    public void clear() {
        myStack.clear();
    }

    @Override
    public String toString() {
        return myStack.toString();
    }

    public String toFileString() {
        String stackString = "";
        for (int i = myStack.size() - 1; i >= 0; i--){
            stackString += myStack.elementAt(i);
            stackString += "\n";
        }
        return stackString;
    }

}
