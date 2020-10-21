package model;
import data_structures.*;

public class ProgramState {

    public StackInterface<Statement> executionStack;
    public DictionaryInterface<String, Value> symbolsTable;
    public ArrayInterface<Value> output;
    //public Statement originalProgram;

    ProgramState(StackInterface<Statement> _stack,
                 DictionaryInterface<String, Value> _symbols,
                 ArrayInterface<Value> _output/*, Statement _program*/){
        executionStack = _stack;
        symbolsTable = _symbols;
        output = _output;
        //originalProgram = _program;   // deepCopy?
    }

    // GETTERS SETTERS

}
