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
    public void logProgramStateExec() throws MyException {
        try{
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            String stackString = currentProgramState.getExecutionStack().toFileString();
            String dictionaryString = currentProgramState.getSymbolsTable().toFileString();
            String arrayString = currentProgramState.getOutput().toFileString();
            String fileTableString = currentProgramState.getFileTable().toFileString();
            logFile.println("Execution Stack:");
            logFile.println(stackString);
            logFile.println("Symbols Table:");
            logFile.println(dictionaryString);
            logFile.println("Output:");
            logFile.println(arrayString);
            logFile.println("File Table:");
            logFile.println(fileTableString);
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
