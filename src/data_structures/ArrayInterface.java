package data_structures;
import model.*;
import java.util.ArrayList;

public interface ArrayInterface<T> {

    public void add(T _object); // adds in front
    public void addToPosition(T _object, int _position);
    public T valueAt(int _position); // value at _position
    public void remove(); // removes from tail
    public void removeFromPosition(int _position); // removes from position
    public void clear();
    public String toString();

}
