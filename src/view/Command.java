package view;

import exceptions.MyException;

public abstract class Command {

    private String key, description;

    public Command(String _key, String _description){
        key = _key;
        description = _description;
    }

    public abstract void execute();

    public String getKey(){
        return key;
    }

    public String getDescription(){
        return description;
    }

}
