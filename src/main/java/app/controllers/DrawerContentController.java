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
            set1.SM2();
            checkStudy.setText("Study at " + set1.getHour() + ":" + set1.getMinute() + " " + set1.getMeridiem() + " " + set1.getDay());
        }
    }

}
