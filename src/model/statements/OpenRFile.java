package model.statements;

import data_structures.DictionaryInterface;
import data_structures.FileTable;
import data_structures.Heap;
import exceptions.MyException;
import model.ProgramState;
import model.expressions.Expression;
import model.types.StringType;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;
import java.io.FileReader;

public class OpenRFile implements Statement{

    public Expression expression;

    public OpenRFile(Expression _expression){
        expression = _expression;
    }

    public String toString(){
        return "openRFile(" + expression.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState _state) throws MyException {
        FileTable<StringValue, BufferedReader> fileTable = _state.getFileTable();
        DictionaryInterface<String, Value> symbolsTable = _state.getSymbolsTable();
        Heap<Integer, Value> heap = _state.getHeap();

        Value val = expression.evaluate(symbolsTable, heap);
        if(val.getType().equals(new StringType())){
            if(fileTable.isDefined(val)){
                throw new MyException("OpenRFile Statement Exception: File path already defined.");
            }
            else{
                try{
                    FileReader fr = new FileReader(val.toString());
                    BufferedReader br = new BufferedReader(fr);
                    fileTable.add(val, br);
                }
                catch(Exception e){
                    throw new MyException("OpenRFile Statement Exception: Error at opening the file at specified path.");
                }
            }
        }
        else{
            throw new MyException("OpenRFile Statement Exception: Expression result is not a String Type.");
        }

        return null;
    }

}
