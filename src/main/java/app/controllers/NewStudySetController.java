package main.java.app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.app.SpacedRep;
import main.java.app.Var;

public class NewStudySetController implements Initializable{

	@FXML
	private BorderPane baseBorder;
	@FXML
	private Label quizletSet;
	@FXML
	private RadioButton quizlet;
	@FXML
	private RadioButton self;
	@FXML
	private JFXTextField afSetEnter;
	@FXML
	private Button afSetAdd;

	@FXML
	public void onSelf() {
		System.out.println("AF");
		afAnimation(50, true);
	}

	@FXML
	public void onQuizlet() {
		afAnimation(0, false);
		try {
			BorderPane newStudySet = FXMLLoader.load(getClass().getResource("/main/resources/app/view/QuizletSearch.fxml"));
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
	public void afSetAdd() {
        SpacedRep set = new SpacedRep();
        set.setTitle(afSetEnter.getText());
        DrawerContentController.studySets.add(set);
        Stage stage = (Stage) baseBorder.getScene().getWindow();
        stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		afAnimation(50, true);
	}
	
	private void afAnimation(int trans, boolean visible) {
		quizlet.setTranslateY(trans);
		afSetEnter.setVisible(visible);
		afSetAdd.setVisible(visible);
	}
}
