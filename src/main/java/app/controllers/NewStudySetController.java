package main.java.app.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.app.Var;

public class NewStudySetController {

	@FXML
	private Label quizletSet;
	
	@FXML
	public void onAF() {
		System.out.println("AF");
	}

	@FXML
	public void onSelf() {
		System.out.println(Var.quizletSelectionTitle);
	}

	@FXML
	public void onQuizlet() {
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
}
