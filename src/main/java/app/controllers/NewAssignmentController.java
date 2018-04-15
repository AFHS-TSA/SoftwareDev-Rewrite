package main.java.app.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.app.Assignments;
import main.java.app.Var;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class NewAssignmentController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXTextField setTitle;
    @FXML
    private JFXComboBox setPriority;
    @FXML
    private JFXDatePicker setDueDate;
    @FXML
    private JFXButton add;
    @FXML
    private JFXButton cancel;


    @FXML
    private void onCancelClicked() {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onAddClicked() {
        Var.assignmentsList.add(new Assignments(setTitle.getText(), setDueDate.toString(), setPriority.getId()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPriority.getItems().addAll("Low", "Medium", "High");
    }
}
