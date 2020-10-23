package exceptions;

public class MyException extends Exception {

    public String message;

    public MyException(String _message){
        this.message = _message;
    }

    public String getMessage(){
        return this.message;
    }

}
