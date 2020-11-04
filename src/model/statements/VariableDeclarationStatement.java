package model.statements;

import data_structures.DictionaryInterface;
import data_structures.StackInterface;
import exceptions.MyException;
import model.ProgramState;
import model.types.BoolType;
import model.types.IntType;
import model.types.Type;
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
            symbolsTable.add(name, new IntValue());
        }
        else{
            if(type instanceof BoolType){
                symbolsTable.add(name, new BoolValue());
            }
            else
                throw new MyException("Variable Declaration Exception: unknown type for declaration of \'" + name + "\'");
        }
        return _state;
    }

}
