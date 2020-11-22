package model.statements;

import exceptions.MyException;
import model.ProgramState;

public class NopStatement implements Statement{

    public String toString(){
        return "nop";
    }

    @Override
    public ProgramState execute(ProgramState _state) throws MyException {
        return _state;
    }

}
