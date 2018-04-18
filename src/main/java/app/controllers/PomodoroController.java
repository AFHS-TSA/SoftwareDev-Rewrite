package main.java.app.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.app.Methods;
import main.java.app.Var;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class PomodoroController implements Initializable {
    @FXML
    Label timerlbl;
    @FXML
    JFXButton start;
    @FXML
    JFXButton stop;
    @FXML
    JFXSlider setTime;
    @FXML
    AnchorPane anchorPane;

    int totalSeconds;

    Timer pom = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    totalSeconds--;
                    int min = totalSeconds/60;
                    int seconds = totalSeconds%60;
                    if (seconds < 10) {
                        timerlbl.setText(min + ":0" + seconds);
                    } else {
                        timerlbl.setText(min + ":" + seconds);
                    }
                    if (totalSeconds == 0) {
                        Var.points += 40;
                        Methods.updatePoints();
                        pom.cancel();
                    }
                }
            });
        }
    };

    @FXML
    private void onStartClicked() {
        totalSeconds = (int) setTime.getValue() * 60;
        pom.scheduleAtFixedRate(task, 10, 1000);
    }

    @FXML
    private void onStopClicked() {
        pom.cancel();
        pom.purge();
        timerlbl.setText("--:--");
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTime.applyCss();
    }
}
