package testing;

import java.util.ArrayList;
import java.util.Stack;

public class Testing {

    public static void main(String args[]){
        String stackString = "";
        Stack<String> stack = new Stack<String>();
        stack.push("Kai Havertz");
        stack.push("Messi");
        stack.push("CR7");
        stack.push("Falcao");
        stack.push("Kamara");
        for (int i = stack.size() - 1; i >= 0; i--){
            stackString += stack.elementAt(i);
            if(i != 0){
                stackString += "\n";
            }
        }
        System.out.println(stackString);
    }

}
