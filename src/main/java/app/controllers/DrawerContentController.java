package main.java.app.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.app.SpacedRep;
import main.java.app.Var;

import java.io.IOException;
import java.util.ArrayList;

public class DrawerContentController {

    @FXML
    JFXCheckBox checkStudy;
    @FXML
    Button newstudy;
    @FXML
    JFXMasonryPane masonry;
    @FXML
    JFXButton pomodoro;
    @FXML
    JFXButton rewards;

    int memStrength = 1;

    public static ArrayList<SpacedRep> studySets = new ArrayList<SpacedRep>();

    @FXML
    private void onCheck() {
        if (checkStudy.isSelected()) {
            studySets.get(0).SM2(memStrength++);
            checkStudy.setText("Study at " + studySets.get(0).getHour() + ":" + studySets.get(0).getMinute() + " " + studySets.get(0).getMeridiem() + " " + studySets.get(0).getDay());
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

}
