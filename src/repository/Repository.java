package repository;

import exceptions.MyException;
import model.ProgramState;

public interface Repository{

    public ProgramState getCurrentProgramState();
    public void logProgramState() throws MyException;

}
