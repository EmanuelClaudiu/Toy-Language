package controller;

import data_structures.MyArray;
import data_structures.MyDictionary;
import data_structures.MyStack;
import data_structures.StackInterface;
import exceptions.MyException;
import model.ProgramState;
import model.statements.Statement;
import model.types.IntType;
import repository.MemoryRepository;
import repository.Repository;

public class Controller {

    public Repository repo = new Repository() {
        @Override
        public ProgramState getCurrentProgramState() {
            return null;
        }
    };

    public Controller(Repository _repo){
        repo = _repo;
    }

    public ProgramState oneStep(ProgramState _state) throws MyException {
        StackInterface<Statement> stack = _state.getExecutionStack();
        if(stack.isEmpty()){
            throw new MyException("ProgramState stack is empty");
        }
        Statement currentStatement = stack.pop();
        return currentStatement.execute(_state);
    }

    public void allStep() throws MyException {
        ProgramState program = repo.getCurrentProgramState();
        System.out.println(program); // printing the curent program
        while(!program.getExecutionStack().isEmpty()){
            program = oneStep(program);
            System.out.println(program);
        }
    }

}

