package repository;

import exceptions.MyException;
import model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MemoryRepository implements Repository{

    public ArrayList<ProgramState> programStateList = new ArrayList<ProgramState>(10);
    public ProgramState currentProgramState;
    public String logFilePath;

    public MemoryRepository(ProgramState _state, String _logFilePath){
        currentProgramState = _state;
        logFilePath = _logFilePath;
    }

    @Override
    public ProgramState getCurrentProgramState() {
        return currentProgramState;
    }

    @Override
    public void logProgramState() throws MyException {
        try{
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            String stackString = currentProgramState.getExecutionStack().toFileString();
            logFile.println("Execution Stack:");
            logFile.println(stackString);
            logFile.println("--------------------");
            logFile.flush();
            logFile.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{

        }
    }

}
