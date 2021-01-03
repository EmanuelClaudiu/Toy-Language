package model.statements;
import data_structures.*;
import exceptions.MyException;
import model.ProgramState;
import model.expressions.*;
import model.types.Type;

import java.io.IOException;

public interface Statement {

    public ProgramState execute(ProgramState _state) throws MyException;
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String,Type> typeEnv) throws MyException;
}
