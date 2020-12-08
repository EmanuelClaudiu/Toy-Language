package repository;

import exceptions.MyException;
import model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MemoryRepository implements Repository{

    public List<ProgramState> programsList = new ArrayList<ProgramState>(2);
    public ProgramState currentProgramState;
    public String logFilePath;

    public MemoryRepository(ProgramState _state, String _logFilePath){
        currentProgramState = _state;
        programsList.add(currentProgramState);
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
            String idString = String.valueOf(currentProgramState.getProgramId());
            String stackString = currentProgramState.getExecutionStack().toFileString();
            String dictionaryString = currentProgramState.getSymbolsTable().toFileString();
            String arrayString = currentProgramState.getOutput().toFileString();
            String fileTableString = currentProgramState.getFileTable().toFileString();
            String heapString = currentProgramState.getHeap().toFileString();
            logFile.println("Program Id:");
            logFile.println(idString);
            logFile.println("Execution Stack:");
            logFile.println(stackString);
            logFile.println("Symbols Table:");
            logFile.println(dictionaryString);
            logFile.println("Output:");
            logFile.println(arrayString);
            logFile.println("File Table:");
            logFile.println(fileTableString);
            logFile.println("Heap:");
            logFile.println(heapString);
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

    @Override
    public void logProgramStateExec(ProgramState _program){
        try{
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            String idString = String.valueOf(_program.getProgramId());
            String stackString = _program.getExecutionStack().toFileString();
            String dictionaryString = _program.getSymbolsTable().toFileString();
            String arrayString = _program.getOutput().toFileString();
            String fileTableString = _program.getFileTable().toFileString();
            String heapString = _program.getHeap().toFileString();
            logFile.println("Program Id:");
            logFile.println(idString);
            logFile.println("Execution Stack:");
            logFile.println(stackString);
            logFile.println("Symbols Table:");
            logFile.println(dictionaryString);
            logFile.println("Output:");
            logFile.println(arrayString);
            logFile.println("File Table:");
            logFile.println(fileTableString);
            logFile.println("Heap:");
            logFile.println(heapString);
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

    @Override
    public List<ProgramState> getProgramList() {
        return programsList;
    }

    @Override
    public void setProgramList(List<ProgramState> _list) {
        programsList = _list;
    }

}
