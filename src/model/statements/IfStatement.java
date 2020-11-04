package model.statements;

import data_structures.DictionaryInterface;
import exceptions.MyException;
import model.ProgramState;
import model.expressions.Expression;
import model.values.BoolValue;
import model.values.Value;

public class IfStatement implements Statement{

    public Expression expression;
    public Statement thenS;
    public Statement elseS;

    public IfStatement(Expression _expression, Statement _thenS, Statement _elseS){
        expression = _expression;
        thenS = _thenS;
        elseS = _elseS;
    }

    public String toString(){
        return "if(" + expression.toString() + ")then(" + thenS.toString() + ")else(" + elseS.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState _state) throws MyException {
        DictionaryInterface<String, Value> symbolsTable = _state.getSymbolsTable();
        Value val = expression.evaluate(symbolsTable);
        if(val.toString() == "true"){
            thenS.execute(_state);
        }
        else{
            if(val.toString() == "false"){
                elseS.execute(_state);
            }
            else{
                throw new MyException("If Statement Exception: failed to evaluate 'if' statement");
            }
        }
        return _state;
    }

}
