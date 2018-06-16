package main.java.app.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.app.Var;

import java.net.URL;
import java.util.ResourceBundle;


public class FlashcardController implements Initializable {

    @FXML
    public JFXButton back;
    @FXML
    public JFXButton forward;
    @FXML
    public JFXButton star;
    @FXML
    public JFXButton flip;
    @FXML
    public Label card;
    @FXML
    public Label title;

    int index = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] arr = {"Srinath", "Vasudevan"}; String[] arr2 = {"Srikant", "Gay Boi"};
        Var.flashSets.add(arr);
        Var.flashSets.add(arr2);
        card.setText(Var.flashSets.get(0)[0]);
    }

    public void onFlip() {
        if (Var.flashSets.get(index)[0].equals(card.getText())) {
            card.setText(Var.flashSets.get(index)[1]);
        } else {
            card.setText(Var.flashSets.get(index)[0]);
        }
    }

    public void onFor() {
        index++;
        if (index > (Var.flashSets.size() - 1)) {
            index = 0;
        }
        card.setText((Var.flashSets.get(index)[0]));
    }

    public void onBack() {
        index--;
        if (index < 0) {
            index = Var.flashSets.size() - 1;
        }
        card.setText((Var.flashSets.get(index)[0]));
    }
}

