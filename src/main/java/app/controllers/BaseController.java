package main.java.app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
        	    } else {
        	    	leftNavDrawer.open();
        	    }
        	 });
        	
        } catch (IOException e1) {
    		e1.printStackTrace();
        }
    }
    
    @SuppressWarnings("static-access")
	private void Shadow() {
    	JFXDepthManager shadow = null;
    	//shadow.setDepth(topBar, Variables.shadow);
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Drawer();
		
		
		basePane.widthProperty().addListener((obs, oldVal, newVal) -> {
			
        	
        	System.out.println(basePane.getWidth());
        	if(basePane.getWidth() < 875) {
        		navBurger.setVisible(true);
        		leftNavDrawer.setVisible(true);
        		rightNavDrawer.setVisible(false);
        	} else {
        		navBurger.setVisible(false);
        		rightNavDrawer.setVisible(true);
        		leftNavDrawer.setVisible(false);
        		
        		try {
    				AnchorPane pane = FXMLLoader.load(getClass().getResource("/main/resources/app/view/DrawerContent.fxml"));
    				rightNavDrawer.getChildren().setAll(pane);
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	}
});
	}
	
}
