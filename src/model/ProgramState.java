package model;
import data_structures.*;
import exceptions.MyException;
import model.statements.Statement;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;
import java.io.File;

public class ProgramState {

    public StackInterface<Statement> executionStack;
    public DictionaryInterface<String, Value> symbolsTable;
    public ArrayInterface<Value> output;
    public FileTable<StringValue, BufferedReader> fileTable;
    public Heap<Integer, Value> heap;
    public int programId = 1;
    //public Statement originalProgram;

    public ProgramState(StackInterface<Statement> _stack,
                        DictionaryInterface<String, Value> _symbols,
                        ArrayInterface<Value> _output,
                        FileTable<StringValue, BufferedReader> _fileTable,
                        Heap<Integer, Value> _heap/*, Statement _program*/){
        executionStack = _stack;
        symbolsTable = _symbols;
        output = _output;
        fileTable = _fileTable;
        heap = _heap;
        //originalProgram = _program;   // deepCopy?
    }

    public boolean isNotCompleted(){
        // returns true when executionStack is not empty, false otherwise
        if(executionStack.getContent().size() != 0){
            return true;
        }
        else{
            return false;
        }
    }

    public ProgramState oneStep() throws MyException{
        if(executionStack.isEmpty())
            throw new MyException("Program State Exception: Program State Stack is empty.");

        Statement currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }

    //GETTERS SETTERS

    public int getProgramId(){
        return programId;
    }

    public void setProgramId(int _id){
        programId = _id;
    }

    public StackInterface<Statement> getExecutionStack(){
        return this.executionStack;
    }

    public DictionaryInterface<String, Value> getSymbolsTable(){
        return this.symbolsTable;
    }

    public ArrayInterface<Value> getOutput(){
        return this.output;
    }

    public FileTable<StringValue, BufferedReader> getFileTable(){
        return this.fileTable;
    }

    public Heap<Integer, Value> getHeap(){
        return this.heap;
    }

    public String toString() {
        return  "Program Id: " + String.valueOf(programId) + "\n"
                + "Execution Stack: " + executionStack.toString() + "\n"
                + "Symbols Table: " + symbolsTable.toString() + "\n"
                + "File Table: " + fileTable.toString() + "\n"
                + "Heap: " + heap.toString() + "\n"
                + "Output: " + output.toString() + "\n";
    }

}
