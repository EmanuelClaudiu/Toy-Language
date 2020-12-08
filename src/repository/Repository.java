package repository;

import exceptions.MyException;
import model.ProgramState;

import java.util.List;

public interface Repository{

    public ProgramState getCurrentProgramState();
    public void logProgramStateExec() throws MyException;
    public void logProgramStateExec(ProgramState _program);
    public List<ProgramState> getProgramList();
    public void setProgramList(List<ProgramState> _list);

}
