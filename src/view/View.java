package view;

import controller.Controller;
import data_structures.MyArray;
import data_structures.MyDictionary;
import data_structures.MyStack;
import exceptions.MyException;
import model.ProgramState;
import model.expressions.ArithmeticExpression;
import model.expressions.RelationalExpression;
import model.expressions.ValueExpression;
import model.expressions.VariableExpression;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.Value;
import repository.MemoryRepository;
import repository.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class View {

    public String code1 = "int v;v=2;Print(v)";
    public String code2 = "int a;int b;a=2+3*5;b=a+1;Print(b)";
    public String code3 = "bool a;int v;a=true;(If a Then v=2 Else v=3);Print(v)";

    public static void flushLogFile(String logFilePath) throws IOException {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath)));
        logFile.flush();
        logFile.close();
    }

    public static void main(String[] args) throws MyException, IOException {
        /*
        form teams: Statement ex1= new CompoundStatement(new VariableDeclarationStatement("v",new IntType()), new CompoundStatement(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        ProgramState state = new ProgramState(); the 3 components should be empty here (make 'clean' method)
        Repository repo = new MemoryRepository(state);
        Controller cntrl = new Controller(repo);
        cntrl.allStep();
        */

        // example 1
        Statement ex1 = new CompoundStatement
                (
                        new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement
                                (
                                        new AssignStatement("v", new ValueExpression(new IntValue(2))),
                                        new PrintStatement(new VariableExpression("v"))
                                )
                );

        // example 2
        Statement ex2 = new CompoundStatement
                (
                new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement
                        (
                        new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement
                                (
                                new AssignStatement
                                        (
                                                "a", new ArithmeticExpression
                                                (
                                                        '+',new ValueExpression(new IntValue(2)), new ArithmeticExpression
                                                        (
                                                            '*',new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))
                                                        )
                                                )
                                        ),
                                new CompoundStatement
                                        (
                                        new AssignStatement("b",new ArithmeticExpression('+',new VariableExpression("a"), new ValueExpression(new IntValue(1)))),
                                        new PrintStatement(new VariableExpression("b"))
                                        )
                                )
                        )
                );

        // example 3
        Statement ex3 = new CompoundStatement
                (
                        new VariableDeclarationStatement("a",new BoolType()),
                        new CompoundStatement
                                (
                                new VariableDeclarationStatement("v", new IntType()),
                                new CompoundStatement
                                        (
                                                new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                                new CompoundStatement
                                                        (
                                                                new IfStatement
                                                                        (
                                                                                new VariableExpression("a"),
                                                                                new AssignStatement("v",new ValueExpression(new IntValue(2))),
                                                                                new AssignStatement("v", new ValueExpression(new IntValue(3)))
                                                                        ),
                                                                new PrintStatement(new VariableExpression("v"))
                                                        )
                                        )
                                )
                );

        // example 4 FLAWED
        Statement ex4 = new CompoundStatement
                (
                        new VariableDeclarationStatement("a",new BoolType()),
                        new CompoundStatement
                                (
                                        new VariableDeclarationStatement("v", new IntType()),
                                        new CompoundStatement
                                                (
                                                        new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                                        new CompoundStatement
                                                                (
                                                                        new IfStatement
                                                                                (
                                                                                        new RelationalExpression("<=", new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(4))),
                                                                                        new AssignStatement("v",new ValueExpression(new IntValue(2))),
                                                                                        new AssignStatement("v", new ValueExpression(new IntValue(3)))
                                                                                ),
                                                                        new PrintStatement(new VariableExpression("v"))
                                                                )
                                                )
                                )
                );

        //initializing structures
        MyStack<Statement> executionStack = new MyStack<Statement>();
        MyDictionary<String, Value> symbolsTable = new MyDictionary<String, Value>();
        MyArray<Value> output = new MyArray<Value>();
        executionStack.clear();
        executionStack.push(ex1);
        symbolsTable.clear();
        output.clear();
        ProgramState programState = new ProgramState(executionStack, symbolsTable, output);
        //reading the log file
        Scanner read = new Scanner(System.in);
        System.out.println("Enter log file path:");
        String logFile = read.nextLine();
        flushLogFile(logFile);
        //initializing layers
        Repository repo = new MemoryRepository(programState, logFile);
        Controller controller = new Controller(repo);
        //executing the program
        controller.allStep();

    }

}
