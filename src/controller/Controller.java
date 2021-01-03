package controller;

import data_structures.*;
import exceptions.MyException;
import model.ProgramState;
import model.types.RefType;
import model.values.RefValue;
import model.values.Value;
import repository.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {

    public ExecutorService executor;
    public Repository repo = new Repository() {
        @Override
        public ProgramState getCurrentProgramState() {
            return null;
        }

        @Override
        public void logProgramStateExec() {

        }

        @Override
        public void logProgramStateExec(ProgramState _program) {

        }

        @Override
        public List<ProgramState> getProgramList() {
            return null;
        }

        @Override
        public void setProgramList(List<ProgramState> _list) {

        }
    };

    public Controller(Repository _repo){
        repo = _repo;
    }

    //GARBAGE COLLECTOR

    public void conservativeGarbageCollector(){
        for(ProgramState state : repo.getProgramList()){
            state.getHeap().setContent(safeGarbageCollector(state.getHeap().getContent()));
        }
    }

    public Map<Integer, Value> safeGarbageCollector(Map<Integer, Value> _heap){
        return _heap.entrySet().stream().filter(this::refInSymbolsTable).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private boolean refInSymbolsTable(Map.Entry<Integer, Value> entry)
    {
        for (ProgramState state : repo.getProgramList()){

            MyDictionary<String, Value> table = (MyDictionary<String, Value>) state.getSymbolsTable();
            boolean existsInSymTable = ( table.getContent().values() ).stream().anyMatch(e -> (refferencesSomething( (Value) e, entry) ));
            if(existsInSymTable == true)
                return true;
        }
        return false;
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

    //EXECUTION

    public void allStep() throws MyException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<ProgramState> programStateList = removeCompletedProgram(repo.getProgramList());
        while(programStateList.size() > 0){
            conservativeGarbageCollector();
            oneStepForAllPrograms(programStateList);
            //remove the completed programs
            programStateList = removeCompletedProgram(repo.getProgramList());
        }
        executor.shutdownNow();
        //HERE the repository still contains at least one Completed Prg
        //and its List<ProgramState> is not empty. Note that oneStepForAllPrograms calls the method
        //setProgramList of repository in order to change the repository

        //update the repository state
        repo.setProgramList(programStateList);
    }

    public List<ProgramState> removeCompletedProgram(List<ProgramState> _programList){
        return _programList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrograms(List<ProgramState> _programsList) throws InterruptedException {
        //printing the programs lit
        System.out.println(_programsList);
        _programsList.forEach(prg -> repo.logProgramStateExec(prg));

        //run concurrently one step for each of the existing Program States
        //-----------------------------------------------------------------
        //prepare the list of callables

        List<Callable<ProgramState>> callList = _programsList.stream()
                .map((ProgramState p) -> (Callable<ProgramState>)(() -> {return p.oneStep();}))
                .collect(Collectors.toList());

        //start the execution of the callables
        //it returns the list of new created ProgramStates (namely threads)
        List<ProgramState> newProgramsList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try{
                        return future.get();
                    }
                    catch(Exception e){
                        //treat posible exceptions thrown by statements execution
                    }
                    return null; //should not reach here though
                })
                .filter(p -> p!=null)
                .collect(Collectors.toList());

        //add the new created threads to the list of existing threads
        _programsList.addAll(newProgramsList);
        //-----------------------------------------------------------------

        //after the execution, print the Program State List into the log file
        _programsList.forEach(prg -> repo.logProgramStateExec(prg));

        //save the current programs in the repository
        repo.setProgramList(_programsList);

    }

}

