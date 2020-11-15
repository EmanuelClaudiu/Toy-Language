package view;

import controller.Controller;
import data_structures.FileTable;
import data_structures.MyArray;
import data_structures.MyDictionary;
import data_structures.MyStack;
import model.ProgramState;
import model.expressions.ArithmeticExpression;
import model.expressions.RelationalExpression;
import model.expressions.ValueExpression;
import model.expressions.VariableExpression;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;
import model.values.Value;
import repository.MemoryRepository;
import repository.Repository;

import java.io.*;

public class Interpretter {

    public String code1 = "int v;v=2;Print(v)";
    public String code2 = "int a;int b;a=2+3*5;b=a+1;Print(b)";
    public String code3 = "bool a;int v;a=true;(If a Then v=2 Else v=3);Print(v)";
    public String code4 = "string v;v=\"file.txt\";openRFile(v);int c;readFile(v,c);readFile(v,c);closeRFile(v);";

    public void flushLogFile(String logFilePath) throws IOException {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath)));
        logFile.flush();
        logFile.close();
    }

    public static void main(String[] args){

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
        MyStack<Statement> executionStack1 = new MyStack<Statement>();
        MyDictionary<String, Value> symbolsTable1 = new MyDictionary<String, Value>();
        MyArray<Value> output1 = new MyArray<Value>();
        FileTable<StringValue, BufferedReader> fileTable1 = new FileTable<StringValue, BufferedReader>();
        executionStack1.clear();
        executionStack1.push(ex1);
        symbolsTable1.clear();
        output1.clear();
        ProgramState programState1 = new ProgramState(executionStack1, symbolsTable1, output1, fileTable1);
        Repository repo1 = new MemoryRepository(programState1, "out1.txt");
        Controller controller1 = new Controller(repo1);

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
        MyStack<Statement> executionStack2 = new MyStack<Statement>();
        MyDictionary<String, Value> symbolsTable2 = new MyDictionary<String, Value>();
        MyArray<Value> output2 = new MyArray<Value>();
        FileTable<StringValue, BufferedReader> fileTable2 = new FileTable<StringValue, BufferedReader>();
        executionStack2.clear();
        executionStack2.push(ex2);
        symbolsTable2.clear();
        output2.clear();
        ProgramState programState2 = new ProgramState(executionStack2, symbolsTable2, output2, fileTable2);
        Repository repo2 = new MemoryRepository(programState2, "out2.txt");
        Controller controller2 = new Controller(repo2);

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
        MyStack<Statement> executionStack3 = new MyStack<Statement>();
        MyDictionary<String, Value> symbolsTable3 = new MyDictionary<String, Value>();
        MyArray<Value> output3 = new MyArray<Value>();
        FileTable<StringValue, BufferedReader> fileTable3 = new FileTable<StringValue, BufferedReader>();
        executionStack3.clear();
        executionStack3.push(ex3);
        symbolsTable3.clear();
        output3.clear();
        ProgramState programState3 = new ProgramState(executionStack3, symbolsTable3, output3, fileTable3);
        Repository repo3 = new MemoryRepository(programState3, "out3.txt");
        Controller controller3 = new Controller(repo3);

        // example 4
        Statement ex4 = new CompoundStatement(
                new VariableDeclarationStatement("v", new StringType()),
                new CompoundStatement(
                        new AssignStatement("v", new ValueExpression(new StringValue("file.txt"))),
                        new CompoundStatement(
                                new OpenRFile(new VariableExpression("v")),
                                new CompoundStatement(
                                        new VariableDeclarationStatement("c", new IntType()),
                                        new CompoundStatement(
                                                new ReadFile(new VariableExpression("v"), "c"),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("c")),
                                                        new CompoundStatement(
                                                                new ReadFile(new VariableExpression("v"), "c"),
                                                                new CompoundStatement(
                                                                        new PrintStatement(new VariableExpression("c")),
                                                                        new CloseRFile(new VariableExpression("v"))
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
        MyStack<Statement> executionStack4 = new MyStack<Statement>();
        MyDictionary<String, Value> symbolsTable4 = new MyDictionary<String, Value>();
        MyArray<Value> output4 = new MyArray<Value>();
        FileTable<StringValue, BufferedReader> fileTable4 = new FileTable<StringValue, BufferedReader>();
        executionStack4.clear();
        executionStack4.push(ex4);
        symbolsTable4.clear();
        output4.clear();
        ProgramState programState4 = new ProgramState(executionStack4, symbolsTable4, output4, fileTable4);
        Repository repo4 = new MemoryRepository(programState4, "out4.txt");
        Controller controller4 = new Controller(repo4);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand((new RunExample("1", ex1.toString(), controller1)));
        menu.addCommand((new RunExample("2", ex2.toString(), controller2)));
        menu.addCommand((new RunExample("3", ex3.toString(), controller3)));
        menu.addCommand((new RunExample("4", ex4.toString(), controller4)));
        menu.show();

    }

}