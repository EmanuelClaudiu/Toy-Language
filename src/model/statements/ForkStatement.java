package model.statements;

import data_structures.*;
import exceptions.MyException;
import model.ProgramState;
import model.values.StringValue;
import model.values.Value;

import java.io.*;

public class ForkStatement implements Statement {

    public Statement innerStatement;

    public ForkStatement(Statement _innerStatement){
        innerStatement = _innerStatement;
    }

    public String toString(){
        return "fork(" + innerStatement.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState _state) throws MyException {
        ProgramState parentProgramState = _state;
        MyStack<Statement> newExecutionStack = new MyStack<Statement>();
        newExecutionStack.push(innerStatement);
        MyDictionary<String, Value> newSymbolsTable = ( (MyDictionary) _state.getSymbolsTable() ).clone();
        MyArray<Value> newOutput = (MyArray<Value>) _state.getOutput();
        FileTable<StringValue, BufferedReader> newFileTable = _state.getFileTable();
        Heap<Integer, Value> newHeap = _state.getHeap();
        ProgramState childProgramState = new ProgramState(newExecutionStack, newSymbolsTable, newOutput, newFileTable, newHeap);

        //keeping track of the orderID in the ThreadOrderID file ----------
        Integer newProgramId;
        PrintWriter threadOrderFile = null;
        try {
            //reading the new order Id
            BufferedReader br = new BufferedReader(new FileReader("ThreadOrderID.txt"));
            newProgramId = Integer.parseInt(br.readLine());
            childProgramState.setProgramId(newProgramId);
            br.close();

            //updating the order number in the file
            threadOrderFile = new PrintWriter(new BufferedWriter(new FileWriter("ThreadOrderID.txt")));
            threadOrderFile.println(newProgramId.toString());
            threadOrderFile.close();

        } catch (IOException e) {
            System.out.println("Fork exception: Could not work with file ThreadOrderID.txt");
        }
        //------------------------------------------------------------------

        return childProgramState;
    }

}
