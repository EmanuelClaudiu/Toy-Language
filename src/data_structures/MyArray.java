package data_structures;
import java.util.*;

public class MyArray<T> implements ArrayInterface{

    ArrayList<T> myList = new ArrayList<T>(10);

    @Override
    public void add(Object _object) {
        myList.add((T) _object);
    }

    @Override
    public void addToPosition(Object _object, int _position) {
        myList.add(_position, (T) _object);
    }

    @Override
    public Object valueAt(int _position) {
        return myList.get(_position);
    }

    @Override
    public void remove() {
        myList.remove(myList.size() - 1);
    }

    @Override
    public void removeFromPosition(int _position) {
        myList.remove(_position);
    }

    @Override
    public void clear() {
        myList.clear();
    }

    @Override
    public String toString() {
        return myList.toString();
    }

    @Override
    public String toFileString() {
        return null;
    }

}
