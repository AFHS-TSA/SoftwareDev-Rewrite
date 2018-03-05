package main.java.app.controllers;

import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import main.java.app.SpacedRep;

public class DrawerContentController {

    SpacedRep set1 = new SpacedRep();

    @FXML
    JFXCheckBox checkStudy;

    @FXML
    private void onCheck() {
        if (checkStudy.isSelected()) {
            checkStudy.setText("Study " + set1.SM2() + " hours from now");
        }
    }

}
