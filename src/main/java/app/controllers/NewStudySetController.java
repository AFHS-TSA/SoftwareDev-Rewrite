package main.java.app.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import main.java.app.SpacedRep;

public class NewStudySetController {

    @FXML
    JFXButton done;
    @FXML
    JFXTextField setName;

    @FXML
    private void onDoneClicked() {
        SpacedRep set = new SpacedRep();
        //set.setTitle(setName.getText());
        DrawerContentController.studySets.add(set);
    }

}
