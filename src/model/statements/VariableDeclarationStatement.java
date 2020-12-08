package model.statements;

import data_structures.DictionaryInterface;
import data_structures.StackInterface;
import exceptions.MyException;
import model.ProgramState;
import model.types.*;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.Value;

import java.util.HashMap;

public class VariableDeclarationStatement implements Statement{

    public String name;
    public Type type;

    public VariableDeclarationStatement(String _name, Type _type){
        name = _name;
        type = _type;
    }

    public String toString(){
        return name + "=" + type.toString();
    }

    @Override
    public ProgramState execute(ProgramState _state) throws MyException {
        DictionaryInterface<String, Value> symbolsTable = _state.getSymbolsTable();
        if(type instanceof IntType) {
            symbolsTable.add(name, new IntType().defaultValue());
        }
        else{
            if(type instanceof BoolType){
                symbolsTable.add(name, new BoolType().defaultValue());
            }
            else {
                if(type instanceof StringType){
                    symbolsTable.add(name, new StringType().defaultValue());
                }
                else{
                    if(type instanceof RefType){
                        symbolsTable.add(name, new RefType(( (RefType) type).getInner()).defaultValue());
                    }
                    else {
                        throw new MyException("Variable Declaration Exception: unknown type for declaration of \'" + name + "\'");
                    }
                }
            }
        }

        return null;
    }

}
