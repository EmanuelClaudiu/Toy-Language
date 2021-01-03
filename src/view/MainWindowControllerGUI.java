package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainWindowControllerGUI {

    @FXML
    public static Label programRunningLabel;

    public static void setProgramRunningLabel(String _new){
        programRunningLabel.setText(_new);
    }

}
