package main.java.app.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.app.Var;

public class RegisterController {

	@FXML
	private JFXTextField userField;
	@FXML
	private JFXPasswordField passField;
	@FXML
	private JFXPasswordField passFieldConfirm;
	@FXML
	private AnchorPane baseAnchor;
	@FXML
	private Label errorLabel;
	public int count = 0;
	
	@FXML
	void onFinish(ActionEvent event) {
		if (passField.getText().equals("") || userField.getText().equals("") || passFieldConfirm.getText().equals("")) {
			System.out.println("Fields are empty");
		} else if (passField.getText().equals(passFieldConfirm.getText())) {
			Stage stage = (Stage) baseAnchor.getScene().getWindow();
			stage.close();

			Connection conn = null;
			try {
				conn = DriverManager.getConnection(Var.sqlURL);
				Statement statement = conn.createStatement();
				
				
				ResultSet rs = statement.executeQuery("select * from users");
		          while(rs.next()) {
		            count++;
		          }
		          count++;
		          statement.executeUpdate("insert into users (id, username, password) values('" + count + "', '" + userField.getText().toString() + "', '" + passField.getText().toString() + "')");
		          statement.executeUpdate("create table " + userField.getText() + " (studysets string, assignName string, duedate string, priority string, type string, points integer)");
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
		} else {
			errorLabel.setText("Your Passwords don't match");
			System.out.println("Doesn't Match");
		}
	}
}
