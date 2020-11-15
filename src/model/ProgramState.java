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
    //public Statement originalProgram;

    public ProgramState(StackInterface<Statement> _stack,
                        DictionaryInterface<String, Value> _symbols,
                        ArrayInterface<Value> _output,
                        FileTable<StringValue, BufferedReader> _fileTable/*, Statement _program*/){
        executionStack = _stack;
        symbolsTable = _symbols;
        output = _output;
        fileTable = _fileTable;
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

    public String toString() {
        return executionStack.toString() + "\n" + symbolsTable.toString() + "\n" + output.toString() + "\n";
    }

}
