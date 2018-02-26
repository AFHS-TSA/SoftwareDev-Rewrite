package main.java.app;
	
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/main/resources/app/view/Base.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/main/resources/app/css/application.css").toExternalForm());
			primaryStage.initStyle(StageStyle.UNIFIED);
		   	primaryStage.setHeight(Var.height);
			primaryStage.setWidth(Var.width);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// Listener for minimum width and height 
			TimeUnit.SECONDS.sleep((long) .1);
			ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
		    	System.out.println("Height: " + primaryStage.getHeight() + " Width: " + primaryStage.getWidth());
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
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
