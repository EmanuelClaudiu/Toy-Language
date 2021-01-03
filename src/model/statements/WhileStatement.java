package model.statements;

import data_structures.DictionaryInterface;
import data_structures.StackInterface;
import exceptions.MyException;
import model.ProgramState;
import model.expressions.Expression;
import model.types.BoolType;
import model.types.Type;
import model.values.BoolValue;
import model.values.Value;

public class WhileStatement implements Statement{

    public Expression expression;
    public Statement statement;

    public WhileStatement(Expression _expression, Statement _statement){
        expression = _expression;
        statement = _statement;
    }

    public String toString(){
        return "(while(" + expression.toString() + ") " + statement.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState _state) throws MyException {
        StackInterface<Statement> stack = _state.getExecutionStack();
        Value expressionEvaluation = expression.evaluate(_state.getSymbolsTable(), _state.getHeap());

        if(expressionEvaluation.getType().equals(new BoolType())){
            if(( (BoolValue) expressionEvaluation).getValue() == false){
                //nothing happens, while statement is ignored
            }
            else{
                stack.push(this);
                stack.push(statement);
            }
        }
        else{
            throw new MyException("While Statement Exception: Conditional expression is not a boolean type.");
        }

        return null;
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnv) throws MyException {
        Type typeExpr = expression.typecheck(typeEnv);
        if(typeExpr.equals(new BoolType())){
            statement.typecheck(typeEnv);
            return typeEnv;
        }
        else
            throw new MyException("While Statement Typecheck Exception: Condition expression is not a boolean type.");
    }

}
