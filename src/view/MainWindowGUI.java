package view;

import controller.Controller;
import data_structures.*;
import exceptions.MyException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ProgramState;
import model.statements.Statement;
import model.values.StringValue;
import model.values.Value;

import java.io.*;
import java.util.*;
import java.util.concurrent.Executors;

public class MainWindowGUI extends Application {

    public Stage window;
    public Scene scene;
    public Controller controller;
    public static Integer programToRun;
    public HBox labelsLayout = new HBox();
    public Label runningProgramLabel;
    public Label numberOfPrgStatesLabel;
    public Button oneStepButton;
    public HBox contentLayout = new HBox();
    public TableView<HeapEntry> heapTableView;
    public ListView<String> outListView;
    public ListView<String> fileTableListView;
    public ListView<String> programStatesListView;
    public HBox prgStateContentLayout = new HBox();
    public TableView<SymTableEntry> symTblTableView;
    public ListView<String> exeStackListView;
    public VBox layout;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle("Main Window");

        ProgramsLoader programsLoader = new ProgramsLoader();
        ArrayList<Controller> controllers = new ArrayList<Controller>();
        controllers = programsLoader.engage();

        //Selecting the program to run
        ArrayList<String> programsString = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("ProgramsList.txt"));
            String program;
            while((program = reader.readLine()) != null) {
                programsString.add(program);
            }
            reader.close();
        }
        catch(IOException _e){
            System.out.println("MainWindowGUI Exception: Could not work with the file ProgramsList.txt.");
        }
        ProgramSelectionWindowGUI programSelectionWindow = new ProgramSelectionWindowGUI();
        programToRun = programSelectionWindow.display(programsString);
        System.out.println(programToRun);
        this.controller = controllers.get(programToRun - 1);
        //-----------------------------


        //1st HBox layout
        runningProgramLabel = new Label("Running program " + programToRun.toString() + "...");
        numberOfPrgStatesLabel = new Label("Number of program states: 1");
        oneStepButton = new Button("One Step");
        //setting up the oneStep action
        //controller.executor = Executors.newFixedThreadPool(2);
        //List<ProgramState> programStateList = controller.removeCompletedProgram(controller.repo.getProgramList());
        oneStepButton.setOnAction(e -> {
            try {
                controller.allStep();
            } catch (MyException myException) {
                myException.printStackTrace();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            setAllViews();
        });
        labelsLayout.setPadding(new Insets(20, 20, 20, 20));
        labelsLayout.setSpacing(30);
        labelsLayout.getChildren().addAll(runningProgramLabel, numberOfPrgStatesLabel, oneStepButton);

        //2nd HBox layout
        TableColumn<HeapEntry, Integer> heapIdColumn = new TableColumn<>("Id.");
        heapIdColumn.setMinWidth(30);
        heapIdColumn.setCellValueFactory(new PropertyValueFactory<HeapEntry, Integer>("id"));
        TableColumn<HeapEntry, String> heapRefColumn = new TableColumn<>("Ref");
        heapRefColumn.setMinWidth(30);
        heapRefColumn.setCellValueFactory(new PropertyValueFactory<>("ref"));
        heapTableView = new TableView<>();
        heapTableView.getColumns().addAll(heapIdColumn, heapRefColumn);
        setHeapTableView();

        outListView = new ListView<>();
        setOutListView();

        fileTableListView = new ListView<>();
        setFileTableListView();

        programStatesListView = new ListView<>();
        setProgramStatesListView();


        contentLayout.setPadding(new Insets(20, 20, 20, 20));
        contentLayout.setSpacing(30);
        contentLayout.getChildren().addAll(heapTableView, outListView, fileTableListView, programStatesListView);

        //3rd HBox layout
        TableColumn<SymTableEntry, String> symTableIdColumn = new TableColumn<>("Id.");
        symTableIdColumn.setMinWidth(20);
        symTableIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<SymTableEntry, Value> symTableValueColumn = new TableColumn<>("Value");
        symTableValueColumn.setMinWidth(20);
        symTableValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        symTblTableView = new TableView<>();
        symTblTableView.getColumns().addAll(symTableIdColumn, symTableValueColumn);
        setSymTblTableView();

        exeStackListView = new ListView<>();
        setExeStackListView();

        prgStateContentLayout.setPadding(new Insets(20, 20, 20, 20));
        prgStateContentLayout.setSpacing(30);
        prgStateContentLayout.getChildren().addAll(symTblTableView, exeStackListView);

        //HBox main layout
        layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(labelsLayout, contentLayout, prgStateContentLayout);

        scene = new Scene(layout, 1500, 800);
        window.setScene(scene);
        window.show();

    }

    //TABLE VIEWS HANDLERS

    public ObservableList<HeapEntry> getHeapEntries(Heap _heap){
        ObservableList<HeapEntry> entries = FXCollections.observableArrayList();
        for (Object id : _heap.getContent().keySet()){
            entries.add(new HeapEntry((Integer) id, (_heap.getContent().get((id))).toString() ));
        }
        return entries;
    }

    public ObservableList<SymTableEntry> getSymTableEntries(DictionaryInterface _symTbl){
        ObservableList<SymTableEntry> entries = FXCollections.observableArrayList();
        for (Object id : _symTbl.getContent().keySet()){
            entries.add(new SymTableEntry((String) id, (Value) _symTbl.getContent().get((id))));
        }
        return entries;
    }

    //SETTERS

    public void setNumberOfPrgStatesLabel(){
        numberOfPrgStatesLabel.setText("Number of program states: " + ((Integer) (controller.repo.getProgramList().size())).toString());
    }

    public void setAllViews(){
        setNumberOfPrgStatesLabel();
        setHeapTableView();
        setOutListView();
        setFileTableListView();
        setProgramStatesListView();
        setSymTblTableView();
        setExeStackListView();
    }

    public void setHeapTableView(){
        ObservableList<HeapEntry> heapEntries = getHeapEntries((Heap) controller.repo.getCurrentProgramState().getHeap());
        heapTableView.setItems(heapEntries);
    }

    public void setOutListView(){
        for(Object entry : controller.repo.getCurrentProgramState().output.getContent()){
            outListView.getItems().add(entry.toString());
        }
    }

    public void setFileTableListView(){
        HashMap<StringValue, BufferedReader> fileTable = controller.repo.getCurrentProgramState().fileTable.getContent();
        for(Object entry : fileTable.keySet()){
            fileTableListView.getItems().add(entry.toString() + " -> " + fileTable.get(entry).toString());
        }
    }

    public void setProgramStatesListView() {
        programStatesListView.getItems().clear();
        Integer numberOfProgramStates = (Integer) (controller.repo.getProgramList().size());
        for(int i = 0; i < numberOfProgramStates; i++){
            programStatesListView.getItems().add("Program State id: " + ((Integer) (i + 1)).toString());
        }
    }

    public void setExeStackListView() {
        exeStackListView.getItems().clear();
        Integer chosenPrgState = programStatesListView.getSelectionModel().getSelectedIndex();
        if(chosenPrgState == -1) chosenPrgState = 0;
        if(controller.repo.getProgramList().size() != 0) {
            Stack<Statement> _stack = controller.repo.getProgramList().get(chosenPrgState).executionStack.getContent();
            for (Statement statement : _stack) {
                exeStackListView.getItems().add(statement.toString());
            }
        }
    }

    public void setSymTblTableView(){
        ObservableList<SymTableEntry> symTableEntries = getSymTableEntries((DictionaryInterface) controller.repo.getCurrentProgramState().getSymbolsTable());
        symTblTableView.setItems(symTableEntries);
    }

}
