package view;

import controller.Controller;
import data_structures.*;
import exceptions.MyException;
import model.ProgramState;
import model.expressions.*;
import model.statements.*;
import model.types.*;
import model.values.*;
import repository.MemoryRepository;
import repository.Repository;

import java.io.*;
import java.sql.Ref;
import java.util.ArrayList;

public class ProgramsLoader {

    public void flushLogFile(String logFilePath) throws IOException {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath)));
        logFile.flush();
        logFile.close();
    }

    public static ArrayList<Controller> engage() throws MyException {

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
        Heap<Integer, Value> heap1 = new Heap<Integer, Value>();
        MyDictionary<String, Type> typeEnv1 = new MyDictionary<String, Type>();
        executionStack1.clear();
        executionStack1.push(ex1);
        symbolsTable1.clear();
        output1.clear();
        heap1.clear();
        ex1.typecheck(typeEnv1);
        ProgramState programState1 = new ProgramState(executionStack1, symbolsTable1, output1, fileTable1, heap1);
        Repository repo1 = new MemoryRepository(programState1, "out1.txt");
        Controller controller1 = new Controller(repo1);

        // example 2
        Statement ex2 = new CompoundStatement
                (
                        new VariableDeclarationStatement("a", new IntType()),
                        new CompoundStatement
                                (
                                        new VariableDeclarationStatement("b", new IntType()),
                                        new CompoundStatement
                                                (
                                                        new AssignStatement
                                                                (
                                                                        "a", new ArithmeticExpression
                                                                        (
                                                                                '+', new ValueExpression(new IntValue(2)), new ArithmeticExpression
                                                                                (
                                                                                        '*', new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))
                                                                                )
                                                                        )
                                                                ),
                                                        new CompoundStatement
                                                                (
                                                                        new AssignStatement("b", new ArithmeticExpression('+', new VariableExpression("a"), new ValueExpression(new IntValue(1)))),
                                                                        new PrintStatement(new VariableExpression("b"))
                                                                )
                                                )
                                )
                );
        MyStack<Statement> executionStack2 = new MyStack<Statement>();
        MyDictionary<String, Value> symbolsTable2 = new MyDictionary<String, Value>();
        MyArray<Value> output2 = new MyArray<Value>();
        FileTable<StringValue, BufferedReader> fileTable2 = new FileTable<StringValue, BufferedReader>();
        Heap<Integer, Value> heap2 = new Heap<Integer, Value>();
        MyDictionary<String, Type> typeEnv2 = new MyDictionary<String, Type>();
        executionStack2.clear();
        executionStack2.push(ex2);
        symbolsTable2.clear();
        output2.clear();
        heap2.clear();
        ex2.typecheck(typeEnv2);
        ProgramState programState2 = new ProgramState(executionStack2, symbolsTable2, output2, fileTable2, heap2);
        Repository repo2 = new MemoryRepository(programState2, "out2.txt");
        Controller controller2 = new Controller(repo2);

        // example 3
        Statement ex3 = new CompoundStatement
                (
                        new VariableDeclarationStatement("a", new BoolType()),
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
                                                                                        new AssignStatement("v", new ValueExpression(new IntValue(2))),
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
        Heap<Integer, Value> heap3 = new Heap<Integer, Value>();
        MyDictionary<String, Type> typeEnv3 = new MyDictionary<String, Type>();
        executionStack3.clear();
        executionStack3.push(ex3);
        symbolsTable3.clear();
        output3.clear();
        heap3.clear();
        ex3.typecheck(typeEnv3);
        ProgramState programState3 = new ProgramState(executionStack3, symbolsTable3, output3, fileTable3, heap3);
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
        Heap<Integer, Value> heap4 = new Heap<Integer, Value>();
        MyDictionary<String, Type> typeEnv4 = new MyDictionary<String, Type>();
        executionStack4.clear();
        executionStack4.push(ex4);
        symbolsTable4.clear();
        output4.clear();
        heap4.clear();
        ex4.typecheck(typeEnv4);
        ProgramState programState4 = new ProgramState(executionStack4, symbolsTable4, output4, fileTable4, heap4);
        Repository repo4 = new MemoryRepository(programState4, "out4.txt");
        Controller controller4 = new Controller(repo4);

        // example 5
        Statement ex5 = new CompoundStatement
                (
                        new VariableDeclarationStatement("a", new IntType()),
                        new CompoundStatement
                                (
                                        new VariableDeclarationStatement("v", new IntType()),
                                        new CompoundStatement
                                                (
                                                        new AssignStatement("a", new ValueExpression(new IntValue(2))),
                                                        new CompoundStatement
                                                                (
                                                                        new IfStatement
                                                                                (
                                                                                        new RelationalExpression(">=", new VariableExpression("a"), new VariableExpression("v")),
                                                                                        new PrintStatement(new ValueExpression(new StringValue("a is greater"))),
                                                                                        new PrintStatement(new ValueExpression(new StringValue("v is greater")))
                                                                                ),
                                                                        new PrintStatement(new ValueExpression(new StringValue("Done")))
                                                                )
                                                )
                                )
                );
        MyStack<Statement> executionStack5 = new MyStack<Statement>();
        MyDictionary<String, Value> symbolsTable5 = new MyDictionary<String, Value>();
        MyArray<Value> output5 = new MyArray<Value>();
        FileTable<StringValue, BufferedReader> fileTable5 = new FileTable<StringValue, BufferedReader>();
        Heap<Integer, Value> heap5 = new Heap<Integer, Value>();
        MyDictionary<String, Type> typeEnv5 = new MyDictionary<String, Type>();
        executionStack5.clear();
        executionStack5.push(ex5);
        symbolsTable5.clear();
        output5.clear();
        heap5.clear();
        ex5.typecheck(typeEnv5);
        ProgramState programState5 = new ProgramState(executionStack5, symbolsTable5, output5, fileTable5, heap5);
        Repository repo5 = new MemoryRepository(programState5, "out5.txt");
        Controller controller5 = new Controller(repo5);

        // example 6 REF
        Statement ex6 = new CompoundStatement
                (
                        new VariableDeclarationStatement("v", new RefType(new IntType())),
                        new CompoundStatement(
                                new NewStatement("v", new ValueExpression(new IntValue(20))),
                                new CompoundStatement(
                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                        new CompoundStatement(
                                                new WriteHeapStatement("v", new ValueExpression(new IntValue(20))),
                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v")))
                                        )
                                )
                        )
                );

        MyStack<Statement> executionStack6 = new MyStack<Statement>();
        MyDictionary<String, Value> symbolsTable6 = new MyDictionary<String, Value>();
        MyArray<Value> output6 = new MyArray<Value>();
        FileTable<StringValue, BufferedReader> fileTable6 = new FileTable<StringValue, BufferedReader>();
        Heap<Integer, Value> heap6 = new Heap<Integer, Value>();
        MyDictionary<String, Type> typeEnv6 = new MyDictionary<String, Type>();
        executionStack6.clear();
        executionStack6.push(ex6);
        symbolsTable6.clear();
        output6.clear();
        heap6.clear();
        ex6.typecheck(typeEnv6);
        ProgramState programState6 = new ProgramState(executionStack6, symbolsTable6, output6, fileTable6, heap6);
        Repository repo6 = new MemoryRepository(programState6, "out6.txt");
        Controller controller6 = new Controller(repo6);

        Statement ex7 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(
                                new WhileStatement(
                                        new RelationalExpression(">", new VariableExpression("v"), new ValueExpression(new IntValue(0))),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new AssignStatement("v", new ArithmeticExpression('-', new VariableExpression("v"), new ValueExpression(new IntValue(1))))
                                        )
                                ),
                                new PrintStatement(new ValueExpression(new IntValue(100)))
                        )
                )
        );

        MyStack<Statement> executionStack7 = new MyStack<Statement>();
        MyDictionary<String, Value> symbolsTable7 = new MyDictionary<String, Value>();
        MyArray<Value> output7 = new MyArray<Value>();
        FileTable<StringValue, BufferedReader> fileTable7 = new FileTable<StringValue, BufferedReader>();
        Heap<Integer, Value> heap7 = new Heap<Integer, Value>();
        MyDictionary<String, Type> typeEnv7 = new MyDictionary<String, Type>();
        executionStack7.clear();
        executionStack7.push(ex7);
        symbolsTable7.clear();
        output7.clear();
        heap7.clear();
        ex7.typecheck(typeEnv7);
        ProgramState programState7 = new ProgramState(executionStack7, symbolsTable7, output7, fileTable7, heap7);
        Repository repo7 = new MemoryRepository(programState7, "out7.txt");
        Controller controller7 = new Controller(repo7);

        // example 8 Garbage Collector
        Statement ex8 = new CompoundStatement
                (
                        new VariableDeclarationStatement("v", new RefType(new IntType())),
                        new CompoundStatement(
                                new NewStatement("v", new ValueExpression(new IntValue(20))),
                                new CompoundStatement(
                                        new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                        new CompoundStatement(
                                                new NewStatement("a", new VariableExpression("v")),
                                                new CompoundStatement(
                                                        new NewStatement("v", new ValueExpression(new IntValue(30))),
                                                        new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))))
                                                )
                                        )
                                )
                        )
                );

        MyStack<Statement> executionStack8 = new MyStack<Statement>();
        MyDictionary<String, Value> symbolsTable8 = new MyDictionary<String, Value>();
        MyArray<Value> output8 = new MyArray<Value>();
        FileTable<StringValue, BufferedReader> fileTable8 = new FileTable<StringValue, BufferedReader>();
        Heap<Integer, Value> heap8 = new Heap<Integer, Value>();
        MyDictionary<String, Type> typeEnv8 = new MyDictionary<String, Type>();
        executionStack8.clear();
        executionStack8.push(ex8);
        symbolsTable8.clear();
        output8.clear();
        heap8.clear();
        ex8.typecheck(typeEnv8);
        ProgramState programState8 = new ProgramState(executionStack8, symbolsTable8, output8, fileTable8, heap8);
        Repository repo8 = new MemoryRepository(programState8, "out8.txt");
        Controller controller8 = new Controller(repo8);

        // example 9 Fork
        Statement ex9 = new CompoundStatement
                (
                        new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(
                                new VariableDeclarationStatement("a", new RefType(new IntType())),
                                new CompoundStatement(
                                        new AssignStatement("v", new ValueExpression(new IntValue(10))),
                                        new CompoundStatement(
                                                new NewStatement("a", new ValueExpression(new IntValue(22))),
                                                new CompoundStatement(
                                                        new ForkStatement(
                                                                new CompoundStatement(
                                                                        new WriteHeapStatement("a", new ValueExpression(new IntValue(30))),
                                                                        new CompoundStatement(
                                                                                new AssignStatement("v", new ValueExpression(new IntValue(32))),
                                                                                new CompoundStatement(
                                                                                        new PrintStatement(new VariableExpression("v")),
                                                                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))
                                                                                )
                                                                        )
                                                                )
                                                        ),
                                                        new CompoundStatement(
                                                                new PrintStatement(new VariableExpression("v")),
                                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))
                                                        )
                                                )
                                        )
                                )
                        )
                );

        MyStack<Statement> executionStack9 = new MyStack<Statement>();
        MyDictionary<String, Value> symbolsTable9 = new MyDictionary<String, Value>();
        MyArray<Value> output9 = new MyArray<Value>();
        FileTable<StringValue, BufferedReader> fileTable9 = new FileTable<StringValue, BufferedReader>();
        Heap<Integer, Value> heap9 = new Heap<Integer, Value>();
        MyDictionary<String, Type> typeEnv9 = new MyDictionary<String, Type>();
        executionStack9.clear();
        executionStack9.push(ex9);
        symbolsTable9.clear();
        output9.clear();
        heap9.clear();
        ex9.typecheck(typeEnv9);
        ProgramState programState9 = new ProgramState(executionStack9, symbolsTable9, output9, fileTable9, heap9);
        Repository repo9 = new MemoryRepository(programState9, "out9.txt");
        Controller controller9 = new Controller(repo9);

        // example 9 Fork
        Statement ex10 = new CompoundStatement
                (
                        new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(
                                new VariableDeclarationStatement("a", new RefType(new IntType())),
                                new CompoundStatement(
                                        new AssignStatement("v", new ValueExpression(new IntValue(10))),
                                        new CompoundStatement(
                                                new NewStatement("a", new ValueExpression(new IntValue(22))),
                                                new CompoundStatement(
                                                        new ForkStatement(
                                                                new CompoundStatement(
                                                                        new WriteHeapStatement("a", new ValueExpression(new IntValue(30))),
                                                                        new CompoundStatement(
                                                                                new AssignStatement("v", new ValueExpression(new IntValue(32))),
                                                                                new CompoundStatement(
                                                                                        new PrintStatement(new VariableExpression("v")),
                                                                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))
                                                                                )
                                                                        )
                                                                )
                                                        ),
                                                        new CompoundStatement(
                                                                new PrintStatement(new VariableExpression("v")),
                                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))
                                                        )
                                                )
                                        )
                                )
                        )
                );

        //setting up the file for keping the thread Id order ----------
        PrintWriter threadOrderFile = null;
        try {
            threadOrderFile = new PrintWriter(new BufferedWriter(new FileWriter("ThreadOrderID.txt")));
            threadOrderFile.println("2");
            threadOrderFile.close();
        } catch (IOException e) {
            System.out.println("Interpreter exception: Could not open file ThreadOrderID.txt");
        }
        //---------------------------------------------------------------

        //Writing programs to file as a string
        ArrayList<String> programsString = new ArrayList();
        programsString.add("0: exit");
        programsString.add("1: " + ex1.toString());
        programsString.add("2: " + ex2.toString());
        programsString.add("3: " + ex3.toString());
        programsString.add("4: " + ex4.toString());
        programsString.add("5: " + ex5.toString());
        programsString.add("6: " + ex6.toString());
        programsString.add("7: " + ex7.toString());
        programsString.add("8: " + ex8.toString());
        programsString.add("9: " + ex9.toString());

        PrintWriter programsFile;
        try {
            programsFile = new PrintWriter(new BufferedWriter(new FileWriter("ProgramsList.txt")));
            for(String program : programsString){
                programsFile.println(program);
            }
            programsFile.close();
        }
        catch (IOException e){
            System.out.println("Error could not work with file ProgramsList.txt");
        }
        //---------------------------------------

        //Returning the list of controllers
        ArrayList<Controller> programs = new ArrayList<Controller>();
        programs.add(controller1);
        programs.add(controller2);
        programs.add(controller3);
        programs.add(controller4);
        programs.add(controller5);
        programs.add(controller6);
        programs.add(controller7);
        programs.add(controller8);
        programs.add(controller9);

        return programs;
    }

}
