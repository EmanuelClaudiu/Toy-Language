package view;

import controller.Controller;
import exceptions.MyException;

public class RunExample extends Command {

    public Controller controller;

    public RunExample(String _key, String _description, Controller _controller){
        super(_key, _description);
        controller = _controller;
    }

    @Override
    public void execute() {
        try{
            controller.allStep();
        }
        catch(Exception e){
            System.out.printf("Error at running the program.");
        }
    }

}
