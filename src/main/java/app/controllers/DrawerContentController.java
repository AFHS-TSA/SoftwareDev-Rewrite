package main.java.app.controllers;

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

    public static ArrayList<SpacedRep> studySets = new ArrayList<SpacedRep>();

    @FXML
    private void onCheck() {
        JFXMasonryPane root = masonry;

        if (checkStudy.isSelected()) {
            //studySets.get(0).SM2();
            //checkStudy.setText("Study " + studySets.get(0).getTitle() + " at " + studySets.get(0).getHour() + ":" + studySets.get(0).getMinute() + " " + studySets.get(0).getMeridiem() + " " + studySets.get(0).getDay());
            root.getChildren().clear();
            for (int i = 0; i < studySets.size(); i++) {
                studySets.get(i).getUpdate();
                System.out.println(studySets.get(i).getMemStrength());
                root.getChildren().add(studySets.get(i).getLabel());
            }
        }
        //studySets.get(0).getUpdate();
        //JFXMasonryPane root = masonry;
        //Label lbl = new Label("hey                    ");
        //root.getChildren().add(lbl);
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

}
