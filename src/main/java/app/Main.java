package main.java.app;
	
import java.beans.XMLEncoder;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.stage.WindowEvent;
import main.java.app.controllers.RewardsController;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/main/resources/app/view/Base.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/main/resources/app/css/application.css").toExternalForm());
			primaryStage.initStyle(StageStyle.UNIFIED);
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		   	primaryStage.setHeight(900);
			primaryStage.setWidth(1250);
			System.out.println("Height: " + screenBounds.getHeight() + "Width: " + screenBounds.getWidth());

			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("main/resources/app/images/AstralFocus_4.png"));
			primaryStage.setResizable(false);
			primaryStage.show();
			
			// Listener for minimum width and height 
			TimeUnit.SECONDS.sleep((long) .1);
			ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
				if(primaryStage.getHeight() == Var.height && primaryStage.getWidth() == Var.width) {
				   	primaryStage.setHeight(Var.height);
					primaryStage.setWidth(Var.width);
				} else {
					if(primaryStage.getHeight() < Var.minHeight) { 
						primaryStage.setHeight(Var.minHeight); 
					} else if(primaryStage.getWidth() < Var.minWidth) { 
						primaryStage.setWidth(Var.minWidth); 
					} else { 
						primaryStage.setResizable(true);
					}
				}
			};		    
		    primaryStage.widthProperty().addListener(stageSizeListener);
		   	primaryStage.heightProperty().addListener(stageSizeListener);
		   	primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					Methods.setPoints();
					Methods.setPlanets();
				}
			});
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
