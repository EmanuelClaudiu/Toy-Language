package model.statements;

import data_structures.StackInterface;
import exceptions.MyException;
import model.ProgramState;

public class CompoundStatement implements Statement{

    public Statement first;
    public Statement second;

    public CompoundStatement(Statement _first, Statement _second){
        first = _first;
        second = _second;
    }

    public String toString(){
        return "(" + first.toString() + ";" + second.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState _state) throws MyException {
        StackInterface<Statement> stack = _state.getExecutionStack();
        stack.push(second);
        stack.push(first);
        return _state;
    }

}
