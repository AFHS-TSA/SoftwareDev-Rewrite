package main.java.app.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.app.Methods;
import main.java.app.SpacedRep;
import main.java.app.Var;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class DrawerContentController implements Initializable{

    @FXML
    JFXCheckBox checkStudy;
    @FXML
    JFXButton newstudy;

    @FXML
    JFXButton pomodoro;
    @FXML
    JFXButton rewards;
    @FXML
    public Label points;
    @FXML
    public Label clock;
    @FXML
    public Label date;
    @FXML
    public JFXListView upcoming;

    int memStrength = 1;

    public static ArrayList<SpacedRep> studySets = new ArrayList<SpacedRep>();

    @FXML
    private void onCheck() {
        if (checkStudy.isSelected()) {
            studySets.get(0).SM2(memStrength++);
            checkStudy.setText("Study " + studySets.get(0).getTitle() + " at " + studySets.get(0).getHour() + ":" + studySets.get(0).getMinute() + " " + studySets.get(0).getMeridiem() + " " + studySets.get(0).getDay());
        }
    }

    @FXML
    private void onNewClicked() {

        try {
            AnchorPane newStudySet = FXMLLoader.load(getClass().getResource("/main/resources/app/view/NewStudySet.fxml"));
            Stage primaryStage = new Stage();
            Scene scene = new Scene(newStudySet);
            primaryStage.initStyle(StageStyle.UNIFIED);
            primaryStage.setHeight(300);
            primaryStage.setWidth(300);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void onPomClicked() {
        try {
            AnchorPane pomodoro = FXMLLoader.load(getClass().getResource("/main/resources/app/view/Pomodoro.fxml"));
            Stage primaryStage = new Stage();
            Scene scene = new Scene(pomodoro);
            primaryStage.initStyle(StageStyle.UNIFIED);
            primaryStage.setHeight(300);
            primaryStage.setWidth(300);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Pomodoro Timer");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onRewardsClicked() {
        try {
            AnchorPane rewards = FXMLLoader.load(getClass().getResource("/main/resources/app/view/Rewards.fxml"));
            Stage primaryStage = new Stage();
            Scene scene = new Scene(rewards);
            primaryStage.initStyle(StageStyle.UNIFIED);
            primaryStage.setHeight(750);
            primaryStage.setWidth(1215);
            primaryStage.setScene(scene);
            primaryStage.setTitle("My Galaxy");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ZoneId zone = ZoneId.systemDefault();
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    upcoming.getItems().clear();
                    ZonedDateTime time = ZonedDateTime.now(zone);
                    clock.setText(Methods.getHour(time) + ":" + Methods.getMinute(time) + " " + Methods.getMeridiem(time));
                    date.setText(Methods.getMonth(time) + " " + time.getDayOfMonth());

                    for (int i = 0; i < Methods.getUrgent(time).size(); i++) {
                        Label lbl = new Label(Methods.getUrgent(time).get(i) + "");
                        lbl.setTextFill(Color.rgb(255, 255, 255));
                        upcoming.getItems().add(lbl);
                    }
                });
            }
        }, 10, 1000);



    }
}
