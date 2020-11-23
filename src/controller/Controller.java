package controller;

import data_structures.MyArray;
import data_structures.MyDictionary;
import data_structures.MyStack;
import data_structures.StackInterface;
import exceptions.MyException;
import model.ProgramState;
import model.statements.Statement;
import model.types.IntType;
import model.values.RefValue;
import model.values.Value;
import repository.MemoryRepository;
import repository.Repository;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Controller {

    public Repository repo = new Repository() {
        @Override
        public ProgramState getCurrentProgramState() {
            return null;
        }

        @Override
        public void logProgramStateExec() {

        }
    };

    public Controller(Repository _repo){
        repo = _repo;
    }

    Map<Integer, Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap){
        return heap.entrySet().stream().filter(e -> symTableAddr.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream().filter(v -> v instanceof RefValue).map(v -> {RefValue v1 = (RefValue) v; return v1.getAddress();}).collect(Collectors.toList());
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
        System.out.println(program); // printing the current program
        // log it in the file
        while(!program.getExecutionStack().isEmpty()){
            program = oneStep(program);
            System.out.println(program);
            repo.logProgramStateExec();
            program.getHeap().setContent(unsafeGarbageCollector(getAddrFromSymTable(program.getSymbolsTable().getContent().values()), program.getHeap().getContent()));
            repo.logProgramStateExec();
        }
    }

}

