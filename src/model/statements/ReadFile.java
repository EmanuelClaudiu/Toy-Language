package model.statements;

import data_structures.DictionaryInterface;
import data_structures.FileTable;
import data_structures.Heap;
import exceptions.MyException;
import model.ProgramState;
import model.expressions.Expression;
import model.types.StringType;
import model.values.IntValue;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class ReadFile implements Statement {

    public Expression expression;
    public String variableName;

    public ReadFile(Expression _expression, String _variableName){
        expression = _expression;
        variableName = _variableName;
    }

    public String toString(){
        return "readFile(" + expression.toString() + "," + variableName.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState _state) throws MyException {
        FileTable<StringValue, BufferedReader> fileTable = _state.getFileTable();
        DictionaryInterface<String, Value> symbolsTable = _state.getSymbolsTable();
        Heap<Integer, Value> heap = _state.getHeap();

        if(symbolsTable.isDefined(variableName)){
            Value val = expression.evaluate(symbolsTable, heap);
            if(val.getType().equals(new StringType())){
                if(fileTable.isDefined(val)) {
                    try {
                        BufferedReader br = (BufferedReader) fileTable.lookup(val);
                        String line = br.readLine();
                        IntValue integer;
                        try{
                            integer = new IntValue(Integer.parseInt(line));
                        }
                        catch(Exception e){
                            throw new MyException("ReadFile Statement Exception: Element read from the file is not a number");
                        }
                        symbolsTable.update(variableName, integer);
                    }
                    catch (IOException e){
                        throw new MyException("ReadFile Statement Exception: Error at reading the file");
                    }
                }
                else{
                    throw new MyException("ReadFile Statement Exception: Specified file path does not exist in the File Table.");
                }
            }
            else{
                throw new MyException("ReadFile Statement Exception: Expression result is not a String Type");
            }
        }
        else{
            throw new MyException("ReadFile Statement Exception: Variable not previously defined.");
        }

        return _state;
    }

}
