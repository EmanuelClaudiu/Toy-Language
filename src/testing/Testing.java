package testing;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Testing{

    static ListView<String> listView;
    static Integer chosenProgram;

    public static Integer display(ArrayList<String> _programs){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Choose program to run.");

        listView = new ListView<>();
        for(String program : _programs){
            listView.getItems().add(program);
        }

        Button button = new Button("Go");
        button.setOnAction(e -> {
            chosenProgram = listView.getSelectionModel().getSelectedIndex();
            window.close();
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(listView, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 600, 400);
        window.setScene(scene);
        window.showAndWait();

        return chosenProgram;
    }

}
