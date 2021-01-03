package model.statements;

import data_structures.DictionaryInterface;
import data_structures.FileTable;
import data_structures.Heap;
import exceptions.MyException;
import model.ProgramState;
import model.expressions.Expression;
import model.types.StringType;
import model.types.Type;
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
        Heap<Integer, Value> heap = _state.getHeap();

        Value val = expression.evaluate(symbolsTable, heap);
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

        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        Type typeExpr = expression.typecheck(typeEnv);
        if(typeExpr.equals(new StringType())){
            return typeEnv;
        }
        else
            throw new MyException("CloseRFile Statement Typecheck Exception: Expression is not a String Type.");
    }

}
