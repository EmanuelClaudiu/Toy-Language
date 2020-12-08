package model.statements;

import data_structures.DictionaryInterface;
import data_structures.Heap;
import exceptions.MyException;
import model.ProgramState;
import model.expressions.Expression;
import model.types.RefType;
import model.values.RefValue;
import model.values.Value;

public class NewStatement implements Statement{

    public String pointerName;
    public Expression expresion;

    public NewStatement(String _pointerName, Expression _expression){
        pointerName = _pointerName;
        expresion = _expression;
    }

    public String toString(){
        return "new(" + pointerName.toString() + "," + expresion.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState _state) throws MyException {
        DictionaryInterface<String, Value> symbolsTable = _state.getSymbolsTable();
        Heap<Integer, Value> heap = _state.getHeap();

        if(symbolsTable.isDefined(pointerName)){
            if(symbolsTable.lookup(pointerName).getType() instanceof RefType){
                Value expressionValue = expresion.evaluate(symbolsTable, heap);
                Value pointerValue = symbolsTable.lookup(pointerName);
                if(expressionValue.getType().equals( ((RefValue) pointerValue).getLocationType())){
                    Integer newEntryAddress = heap.addNewEntry(expressionValue);
                    symbolsTable.update(pointerName, new RefValue(newEntryAddress, ((RefValue) pointerValue).getLocationType()));
                }
                else{
                    throw new MyException("New Statement Exception: Type of the assigned expression does not match with the declared type of the Ref.");
                }
            }
            else{
                throw new MyException("New Statement Exception: Type of variable \'" + pointerName + "\' is not a Ref Type.");
            }
        }
        else{
            throw new MyException("New Statement Exception: Variable name \'" + pointerName + "\' has not been previously defined in the symbols table.");
        }

        return null;
    }

}
