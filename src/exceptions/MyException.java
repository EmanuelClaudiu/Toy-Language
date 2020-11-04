package exceptions;

public class MyException extends Exception {

    public String message;

    public MyException(String _message){
        this.message = _message;
        speak();
    }

    public void speak(){
        System.out.println("\033[0;31m" + message);
    }

}
