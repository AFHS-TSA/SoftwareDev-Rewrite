package main.java.app.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.sun.javafx.scene.SceneHelper;
import com.sun.javafx.stage.StageHelper;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.util.Duration;
import main.java.app.Assignments;
import main.java.app.Methods;
import main.java.app.Var;
import org.controlsfx.control.Notifications;
import org.Quizlet;

public class BaseController implements Initializable {

	@FXML
	private BorderPane basePane;
	@FXML
	private AnchorPane rightNavDrawer;
	@FXML
	private JFXListView listView;
	@FXML
	private JFXButton addNew;
	@FXML
	private JFXTextField setTitle;
	@FXML
	private JFXComboBox setPriority;
	@FXML
	private JFXDatePicker setDueDate;
	@FXML
	private JFXComboBox setType;
	@FXML
	public Label points;
	@FXML
	private Label quote;
	@FXML
	private JFXButton newQuote;
	static int count = 0;
	/*@FXML
	private JFXDrawer leftNavDrawer;
	@FXML
	private JFXHamburger navBurger;*/

	
    /*private void Drawer() {
        try {    	
    		AnchorPane drawerContent = FXMLLoader.load(getClass().getResource("/main/resources/app/view/DrawerContent.fxml"));
    		leftNavDrawer.setSidePane(drawerContent);
        	HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(navBurger);
        	burgerTask.setRate(-1);
        	navBurger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{
        	    burgerTask.setRate(burgerTask.getRate()*-1);
        	    burgerTask.play();
        	    
        	    if(leftNavDrawer.isShown()) {
        	    	leftNavDrawer.close();
        	    	leftNavDrawer.setDisable(true);
        	    } else {
        	    	leftNavDrawer.open();
        	    	leftNavDrawer.setDisable(false);
        	    }
        	 });
        	
        } catch (IOException e1) {
    		e1.printStackTrace();
        }
    }
    
    @FXML
    public void test() {
    	System.out.println(rightNavDrawer.getId());
    }*/

    @FXML
	private void onNewQuote() {
    	updateQuote();
	}

    @FXML
	private void onNewAdded() {
		Var.assignmentsList.add(new Assignments(setTitle.getText(), setDueDate.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")), setPriority.getSelectionModel().getSelectedItem().toString(), setType.getSelectionModel().getSelectedItem().toString()));
		updateAssign(Var.assignmentsList.size() - 1);
		
		Connection conn = null;
        try {
            conn = DriverManager.getConnection(Var.sqlURL);
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("select * from users");
            statement.executeUpdate("insert into " + Var.username + " (assignName, duedate, priority, type) values ('" + setTitle.getText() + "', '" + setDueDate.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")) + "', '" + setPriority.getSelectionModel().getSelectedItem().toString() + "', '" + setType.getSelectionModel().getSelectedItem().toString() + "')");
            
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

	public void updateAssign(int i) {
		
			JFXCheckBox chkBx = new JFXCheckBox(Var.assignmentsList.get(i).title + "");
			Label due = new Label("Due: " + Var.assignmentsList.get(i).dueDate);
			due.setTextFill(Color.rgb(255, 255, 255, .7));
			Label type = new Label(Var.assignmentsList.get(i).type + " Assignment");
			type.setTextFill(Color.rgb(255, 255, 255, .7));
			if (Var.assignmentsList.get(i).type.equals("Major")) {
				type.setStyle("-fx-font-weight: bold");
			} else if (Var.assignmentsList.get(i).type.equals("Minor")) {
				//type.setStyle("-fx-font-weight: bold");
			} else if (Var.assignmentsList.get(i).type.equals("Regular")) {
				type.setStyle("-fx-font-style: italic");
			} else {System.out.println("A");}

			Label pri = new Label("Priority: " + Var.assignmentsList.get(i).priority);
			if (Var.assignmentsList.get(i).priority.equals("High")) {
				pri.setTextFill(Color.rgb(255, 0, 0, .7));
			} else if (Var.assignmentsList.get(i).priority.equals("Medium")) {
				pri.setTextFill(Color.rgb(255, 165, 0, .7));
			} else if (Var.assignmentsList.get(i).priority.equals("Low")) {
				pri.setTextFill(Color.rgb(255, 255, 0, .7));
			} else {
				pri.setTextFill(Color.rgb(255, 255, 255, .7));
			}

			chkBx.setTextFill(Color.rgb(255, 255, 255));
			chkBx.setFont(Font.font("System", 18));
			chkBx.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (chkBx.isSelected()) {
					listView.getItems().remove(chkBx);
					listView.getItems().remove(due);
					listView.getItems().remove(type);
					listView.getItems().remove(pri);
		            String chkText = chkBx.getText();

					Connection conn = null;
			        try {
			            conn = DriverManager.getConnection(Var.sqlURL);
			            Statement statement = conn.createStatement();
			            statement.executeQuery("delete from " + Var.username + " where assignName = '" + chkText + "'");
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

					Notifications.create()
							.title("Assignment Completed")
							.darkStyle()
							.text(chkBx.getText() + " completed")
							.hideAfter(Duration.seconds(5))
							.showConfirm();
					if (Var.assignmentsList.get(i).type.equals("Major")) {
						Var.points += 75;
					} else if (Var.assignmentsList.get(i).type.equals("Minor")) {
						Var.points += 50;
					} else if (Var.assignmentsList.get(i).type.equals("Regular")) {
						Var.points += 25;
					}
					Methods.setPoints();
					updateLabel();
				}
			}
		});

			listView.getItems().add(chkBx);
			listView.getItems().add(due);
			listView.getItems().add(type);
			listView.getItems().add(pri);


	}

	public void updateLabel() {
    	Methods.setPoints();
		Methods.updatePoints();
    	points.setText("Points: " + Var.points);
	}

	public void updateQuote() {
    	quote.setText(Methods.getQuote());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Connection conn = null;
        try {
            conn = DriverManager.getConnection(Var.sqlURL);
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("select * from " + Var.username);
            while(rs.next()) {
            	String assignName = rs.getString("assignName");
            	String duedate = rs.getString("duedate");
            	String priority = rs.getString("priority");
            	String type = rs.getString("type");
            	if(assignName == null || duedate == null || priority == null || type == null) {
            		System.out.println("null");
            	} else {
            		Var.assignmentsList.add(new Assignments(assignName, duedate, priority, type));
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
		Preferences preferences = Preferences.userNodeForPackage(Methods.class);
		Random random = new Random();
		int randomInt = random.nextInt((24-0) + 1);
		preferences.putInt("RandomNumber", randomInt);
		try {
			preferences.exportNode(new FileOutputStream("quote.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}

    	listView.setExpanded(true);

		setPriority.getItems().addAll("Low", "Medium", "High");
		setType.getItems().addAll("Major", "Minor", "Regular");
		Methods.updatePoints();

		quote.setText(Methods.getQuote());

		for (int i = 0; i < Var.assignmentsList.size(); i++) {
			updateAssign(i);
		}

    	//Drawer();
		rightNavDrawer.setId("shrink");
		
		// Listener for the Width of the Base window.
		basePane.widthProperty().addListener((obs, oldVal, newVal) -> {

			// Right Drawer Expander and Shrinker based on rightNavDrawer ID.
			if(rightNavDrawer.getId() == "shrink") {
				try {
					AnchorPane pane = FXMLLoader.load(getClass().getResource("/main/resources/app/view/DrawerContent.fxml"));
					rightNavDrawer.getChildren().setAll(pane);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rightNavDrawer.setId("expand");
			}

		Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					Platform.runLater(() -> {
						Methods.setPoints();
						updateLabel();
					});
				}
			}, 10, 1000);

        	// Change the drawer layout based on the size of the window.
        	/*if(basePane.getWidth() < 875) {
        		navBurger.setVisible(true);
        		leftNavDrawer.setVisible(true);
        		basePane.getChildren().remove(rightNavDrawer);
        	} else {
        		navBurger.setVisible(false);
            	leftNavDrawer.setVisible(false);
            	basePane.getChildren().add(rightNavDrawer);
            	basePane.setRight(rightNavDrawer);
        	}*/
		});
			}

}
