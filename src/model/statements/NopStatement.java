package model.statements;

import data_structures.DictionaryInterface;
import exceptions.MyException;
import model.ProgramState;
import model.types.Type;

public class NopStatement implements Statement{

    public String toString(){
        return "nop";
    }

    @Override
    public ProgramState execute(ProgramState _state) throws MyException {
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

}
