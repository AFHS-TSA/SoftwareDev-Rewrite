package main.java.app.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.sun.javafx.scene.SceneHelper;
import com.sun.javafx.stage.StageHelper;

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
	public Label points;
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
	private void onNewAdded() {
		Var.assignmentsList.add(new Assignments(setTitle.getText(), setDueDate.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")), setPriority.getSelectionModel().getSelectedItem().toString()));
		updateAssign(Var.assignmentsList.size() - 1);
	}

	public void updateAssign(int i) {
			JFXCheckBox chkBx = new JFXCheckBox(Var.assignmentsList.get(i).title + "");
			Label due = new Label("Due: " + Var.assignmentsList.get(i).dueDate);
			due.setTextFill(Color.rgb(255, 255, 255, .7));
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
					listView.getItems().remove(pri);
					Var.assignmentsList.remove(i);
					Notifications.create()
							.title("Assignment Completed")
							.darkStyle()
							.text(chkBx.getText() + " completed")
							.hideAfter(Duration.seconds(5))
							.showConfirm();
					Var.points += 25;
					updateLabel();
					//DrawerContentController dc = new DrawerContentController();
					//dc.update();
				}
			}
		});

			listView.getItems().add(chkBx);
			listView.getItems().add(due);
			listView.getItems().add(pri);


	}

	public void updateLabel() {
    	Methods.setPoints();
		Methods.updatePoints();
    	points.setText("Points: " + Var.points);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		setPriority.getItems().addAll("Low", "Medium", "High");
		Methods.updatePoints();

		updateLabel();

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
		
        	// Change the drawer layout based on the size of the window.
        	/*f(basePane.getWidth() < 875) {
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
