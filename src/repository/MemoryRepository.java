package repository;

import model.ProgramState;
import java.util.ArrayList;

public class MemoryRepository implements Repository{

    public ArrayList<ProgramState> programStateList = new ArrayList<ProgramState>(10);
    public ProgramState currentProgramState;

    public MemoryRepository(ProgramState _state){
        currentProgramState = _state;
    }

    @Override
    public ProgramState getCurrentProgramState() {
        return currentProgramState;
    }

}
