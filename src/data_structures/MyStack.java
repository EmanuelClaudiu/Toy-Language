package data_structures;
import java.util.*;

public class MyStack<T> implements StackInterface {

    Stack<T> myStack = new Stack<T>();

    @Override
    public T pop() {
        return myStack.pop();
    }

    @Override
    public void push(Object _object) {
        myStack.push((T) _object);
    }

}
