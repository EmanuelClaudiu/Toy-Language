package controller;

import data_structures.*;
import exceptions.MyException;
import model.ProgramState;
import model.statements.Statement;
import model.types.RefType;
import model.values.RefValue;
import model.values.Value;
import repository.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
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

    public Map<Integer, Value> safeGarbageCollector(Map<Integer, Value> _heap){
        return _heap.entrySet().stream().filter(this::refInSymbolsTable).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private boolean refInSymbolsTable(Map.Entry<Integer, Value> entry)
    {
        MyDictionary<String, Value> table = (MyDictionary<String, Value>) repo.getCurrentProgramState().getSymbolsTable();
        return ( table.getContent().values() ).stream().anyMatch(e -> (refferencesSomething( (Value) e, entry) ));
    }

    private boolean refferencesSomething(Value v1, Map.Entry<Integer, Value> entry)
    {
        if(v1 instanceof RefValue)
        {
            RefValue value = (RefValue) v1;
            int address = value.getAddress();
            if(address == entry.getKey())
                return true;

            Heap<Integer, Value> heap = repo.getCurrentProgramState().getHeap();
            if(value.getLocationType() instanceof RefType)
                return refferencesSomething((Value) heap.lookup(address), entry);
        }
        return false;
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
            program.getHeap().setContent(safeGarbageCollector(program.getHeap().getContent()));
            repo.logProgramStateExec();
        }
    }

}

