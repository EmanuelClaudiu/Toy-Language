package model;
import data_structures.*;
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

    //GETTERS SETTERS

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
        return "Execution Stack: " + executionStack.toString() + "\n" + "Symbols Table: " + symbolsTable.toString() + "\n" + "File Table: " + fileTable.toString() + "\n" + "Heap: " + heap.toString() + "\n" + "Output: " + output.toString() + "\n";
    }

}
