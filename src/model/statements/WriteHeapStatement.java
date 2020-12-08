package model.statements;

import data_structures.DictionaryInterface;
import data_structures.Heap;
import exceptions.MyException;
import model.ProgramState;
import model.expressions.Expression;
import model.types.RefType;
import model.values.RefValue;
import model.values.Value;

public class WriteHeapStatement implements Statement{

    public String variable;
    public Expression expression;

    public WriteHeapStatement(String _variable, Expression _expression){
        variable = _variable;
        expression = _expression;
    }

    public String toString(){
        return "wH(" + variable + "," + expression.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState _state) throws MyException {
        DictionaryInterface<String, Value> symbolsTable = _state.getSymbolsTable();
        Heap<Integer, Value> heap = _state.getHeap();

        if(symbolsTable.isDefined(variable)){
            if(symbolsTable.lookup(variable).getType() instanceof RefType){
                RefValue variableValue = (RefValue) symbolsTable.lookup(variable);
                if(heap.isDefined(variableValue.getAddress())){
                    Value e = expression.evaluate(symbolsTable, heap);
                    if(e.getType().equals(variableValue.getLocationType())){
                        heap.update(variableValue.getAddress(), e);
                    }
                    else{
                        throw new MyException("Write Heap Statement Exception: The types of the variable value and the expression result do not match.");
                    }
                }
                else{
                    throw new MyException("Write Heap Statement Exception: The associated address does not exist in the heap.");
                }
            }
            else{
                throw new MyException("Write Heap Statement Exception: Associated value in the symbols table is not a Ref Type.");
            }
        }
        else{
            throw new MyException("Write Heap Statement Exception: Variable of Ref Type is not defined in the symbols table.");
        }

        return null;
    }

}
