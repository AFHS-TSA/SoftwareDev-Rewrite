package main.java.app.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import main.java.app.Methods;
import main.java.app.Var;

public class LoginController {

	@FXML
	private BorderPane baseBorder;
	@FXML
	private JFXTextField userField;
	@FXML
	private JFXPasswordField passField;

	@FXML
	void onLogin(ActionEvent event) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(Var.sqlURL);
			Statement statement = conn.createStatement();

			ResultSet rs = statement.executeQuery("select * from users");
			while (rs.next()) {
				if (userField.getText().equals(rs.getString("username"))) {
					System.out.println("Logged");
					if (passField.getText().equals(rs.getString("password"))) {
						Stage stage = (Stage) baseBorder.getScene().getWindow();
			            stage.close();
			            Var.points = rs.getInt("points");
			            Methods.setPoints();
			            System.out.println(rs.getInt("points"));
						try {
							BorderPane newStudySet = FXMLLoader.load(getClass().getResource("/main/resources/app/view/Base.fxml"));
							Stage primaryStage = new Stage();
							Scene scene = new Scene(newStudySet);
							primaryStage.setResizable(false);
							primaryStage.setScene(scene);
							primaryStage.getIcons().add(new Image("main/resources/app/images/AstralFocus_4.png"));
							primaryStage.initStyle(StageStyle.DECORATED);
							primaryStage.show();
							primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
								@Override
								public void handle(WindowEvent event) {
									Methods.setPoints();
									Methods.setPlanets();
								}
							});
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						System.out.println("Failed");
					}
				} else {
					System.out.println("failed");
				}

			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	
	@FXML
	void onRegister(ActionEvent event) {
		try {
            AnchorPane newStudySet = FXMLLoader.load(getClass().getResource("/main/resources/app/view/Register.fxml"));
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