package model.statements;

import data_structures.DictionaryInterface;
import data_structures.StackInterface;
import exceptions.MyException;
import model.ProgramState;
import model.types.Type;

public class CompoundStatement implements Statement{

    public Statement first;
    public Statement second;

    public CompoundStatement(Statement _first, Statement _second){
        first = _first;
        second = _second;
    }

    public String toString(){
        return  first.toString() + ";" + second.toString();
    }

    @Override
    public ProgramState execute(ProgramState _state) throws MyException {
        StackInterface<Statement> stack = _state.getExecutionStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        //MyIDictionary<String,Type> typEnv1 = first.typecheck(typeEnv);
        // MyIDictionary<String,Type> typEnv2 = snd.typecheck(typEnv1);
        // return typEnv2;
        return second.typecheck(first.typecheck(typeEnv));
    }

}
