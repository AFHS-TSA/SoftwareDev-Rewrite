package main.java.app.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import main.java.app.Methods;
import main.java.app.SpacedRep;
import main.java.app.Var;
import org.controlsfx.control.Notifications;

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
    @FXML
    public JFXListView studysets;
    @FXML
    public JFXButton flashcard;

    @FXML
    private void onFlash() {
        try {
            AnchorPane flash = FXMLLoader.load(getClass().getResource("/main/resources/app/view/Flashcard.fxml"));
            Stage primaryStage = new Stage();
            Scene scene = new Scene(flash);
            primaryStage.setHeight(425);
            primaryStage.setWidth(600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Flashcard");
            primaryStage.getIcons().add(new Image("main/resources/app/images/AstralFocus_4.png"));
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onNewClicked() {

        try {
            BorderPane newStudySet = FXMLLoader.load(getClass().getResource("/main/resources/app/view/NewStudySet.fxml"));
            Stage primaryStage = new Stage();
            Scene scene = new Scene(newStudySet);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image("main/resources/app/images/AstralFocus_4.png"));
            primaryStage.initStyle(StageStyle.DECORATED);
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
            primaryStage.setHeight(300);
            primaryStage.setWidth(300);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Pomodoro Timer");
            primaryStage.getIcons().add(new Image("main/resources/app/images/AstralFocus_4.png"));
            primaryStage.initStyle(StageStyle.DECORATED);
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
            primaryStage.setHeight(750);
            primaryStage.setWidth(1215);
            primaryStage.setScene(scene);
            primaryStage.setTitle("My Galaxy");
            primaryStage.getIcons().add(new Image("main/resources/app/images/AstralFocus_4.png"));
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateSets(int i) {

            JFXCheckBox cb = new JFXCheckBox("Study " + Var.studySets.get(i).getTitle() + " at " + Var.studySets.get(i).getHour() + ":" + Var.studySets.get(i).getMinute() + " " + Var.studySets.get(i).getMeridiem() + " " + Var.studySets.get(i).getDay());

            cb.setTextFill(Color.rgb(255, 255, 255));
            cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (cb.isSelected()) {
                        Var.studySets.get(i).SM2();
                        cb.setText("Study " + Var.studySets.get(i).getTitle() + " at " + Var.studySets.get(i).getHour() + ":" + Var.studySets.get(i).getMinute() + " " + Var.studySets.get(i).getMeridiem() + " " + Var.studySets.get(i).getDay());
                        Var.points += 15;
                        Methods.setPoints();
                        Notifications.create()
                                .title("Assignment Completed")
                                .darkStyle()
                                .text(Var.studySets.get(i).getTitle() + " studied")
                                .hideAfter(Duration.seconds(5))
                                .showConfirm();
                        cb.setSelected(false);
                    }
                }
            });
            studysets.getItems().add(cb);

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
                    studysets.getItems().clear();
                    ZonedDateTime time = ZonedDateTime.now(zone);
                    clock.setText(Methods.getHour(time) + ":" + Methods.getMinute(time) + " " + Methods.getMeridiem(time));
                    date.setText(Methods.getMonth(time) + " " + time.getDayOfMonth());

                    for (int i = 0; i < Methods.getUrgent(time).size(); i++) {
                        Label lbl = new Label(Methods.getUrgent(time).get(i) + "");
                        lbl.setTextFill(Color.rgb(255, 255, 255));
                        upcoming.getItems().add(lbl);
                    }

                    for (int i = 0; i < Var.studySets.size(); i++) {
                        updateSets(i);
                    }


                });
            }
        }, 10, 1000);



    }
}
