package main.java.app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.sun.javafx.scene.SceneHelper;
import com.sun.javafx.stage.StageHelper;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import main.java.app.Assignments;
import main.java.app.Var;

public class BaseController implements Initializable {

	@FXML
	private JFXButton newAssign;
	@FXML
	private BorderPane basePane;
	@FXML
	private AnchorPane rightNavDrawer;
	@FXML
	private JFXListView listView;
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
	private void onNewClicked() {
		try {
			AnchorPane assignment = FXMLLoader.load(getClass().getResource("/main/resources/app/view/NewAssignment.fxml"));
			Stage primaryStage = new Stage();
			Scene scene = new Scene(assignment);
			primaryStage.initStyle(StageStyle.UNIFIED);
			primaryStage.setHeight(250);
			primaryStage.setWidth(511);
			primaryStage.setScene(scene);
			primaryStage.setTitle("New Assignment");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		for (int i = 0; i < 5; i++) {
			CheckBox chkBx = new CheckBox("Assignment " + i);
			chkBx.setTextFill(Color.rgb(255, 255, 255));
			listView.getItems().add(chkBx);
			chkBx.selectedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if (chkBx.isSelected()) {
						listView.getItems().remove(chkBx);
					}
				}
			});
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
