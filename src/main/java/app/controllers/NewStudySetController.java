package main.java.app.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.app.SpacedRep;

public class NewStudySetController {

    @FXML
    JFXButton done;
    @FXML
    JFXTextField setName;
    @FXML
    AnchorPane anchorPane;

    @FXML
    private void onDoneClicked() {
        SpacedRep set = new SpacedRep();
        set.setTitle(setName.getText());
        DrawerContentController.studySets.add(set);
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }

}
