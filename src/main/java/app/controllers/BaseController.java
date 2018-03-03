package main.java.app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.sun.javafx.scene.SceneHelper;
import com.sun.javafx.stage.StageHelper;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class BaseController implements Initializable {

	@FXML
	private BorderPane basePane;
	@FXML
	private AnchorPane rightNavDrawer;
	@FXML
	private JFXDrawer leftNavDrawer;
	@FXML
	private JFXHamburger navBurger;
	
    private void Drawer() {
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
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Drawer();
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
        	if(basePane.getWidth() < 875) {
        		navBurger.setVisible(true);
        		leftNavDrawer.setVisible(true);
        		basePane.getChildren().remove(rightNavDrawer);
        	} else {
        		navBurger.setVisible(false);
            	leftNavDrawer.setVisible(false);
            	basePane.getChildren().add(rightNavDrawer);
            	basePane.setRight(rightNavDrawer);
        	}
		});
	}
	
}
