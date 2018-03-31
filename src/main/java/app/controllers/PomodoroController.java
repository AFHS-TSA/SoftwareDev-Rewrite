package main.java.app.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Timer;
import java.util.TimerTask;

public class PomodoroController {
    @FXML
    Label timerlbl;
    @FXML
    JFXButton start;
    @FXML
    JFXButton stop;
    @FXML
    JFXSlider setTime;

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
    }
}
