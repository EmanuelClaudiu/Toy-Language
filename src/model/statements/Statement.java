package model.statements;
import data_structures.*;
import exceptions.MyException;
import model.ProgramState;
import model.expressions.*;

public interface Statement {

    public ProgramState execute(ProgramState _state) throws MyException;

}
