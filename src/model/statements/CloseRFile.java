package model.statements;

import data_structures.DictionaryInterface;
import data_structures.FileTable;
import exceptions.MyException;
import model.ProgramState;
import model.expressions.Expression;
import model.types.StringType;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements Statement{

    public Expression expression;

    public CloseRFile(Expression _expression){
        expression = _expression;
    }

    public String toString(){
        return "closeRFile(" + expression.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState _state) throws MyException {
        FileTable<StringValue, BufferedReader> fileTable = _state.getFileTable();
        DictionaryInterface<String, Value> symbolsTable = _state.getSymbolsTable();
        Value val = expression.evaluate(symbolsTable);
        if(val.getType().equals(new StringType())){
            if(fileTable.isDefined(val)){
                try {
                    BufferedReader br = (BufferedReader) fileTable.lookup(val);
                    br.close();
                    fileTable.remove(val);
                }
                catch(IOException e){
                    throw new MyException("CloseRFile Statement Exception: Error at closing the buffer.");
                }
            }
            else{
                throw new MyException("CloseRFile Statement Exception: Specified file path does not exist in the File Table.");
            }
        }
        else{
            throw new MyException("CloseRFile Statement Exception: Expression result is not a String Type.");
        }

        return _state;
    }

}
