package main.java.app.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.app.SpacedRep;
import main.java.app.Var;

import java.io.IOException;

import org.Quizlet;
import org.json.JSONException;

public class NewStudySetController{

    @FXML
    JFXButton done;
    @FXML
    JFXTextField setName;
    @FXML
    AnchorPane anchorPane;
    @FXML
    JFXRadioButton AFSet;
    @FXML
    JFXRadioButton Quizlet;
    @FXML
    JFXRadioButton Self;
    @FXML
    JFXTextField quizSearch;
    @FXML
    JFXButton search;
    @FXML
    
    
    
    int isClicked = 0;
    @FXML
    private void onSearchClicked(){
    	try{
        	Quizlet.setSearch("UNNa57NRpT", "Chemistry");
        	int[]searchID = Quizlet.getSearchID();
        	for(int realid:searchID){
        		System.out.println(realid);
        	}
    	}
    	catch(IOException | JSONException e)
        		{
    		e.printStackTrace();
        	}
        }
    
    
    @FXML
    private void onDoneClicked() {
    	SpacedRep set = new SpacedRep();
    	switch(isClicked){
    	case 1:
    		set.setTitle(setName.getText());
    		Var.studySets.add(set);
    		set.setType("Astral Focus Set");
    		break;
    	case 2: 
    		set.setTitle(setName.getText());
    		Var.studySets.add(set);
    		set.setType("Self Made Set");
    		break;
    	case 3: 
    		
    		Var.studySets.add(set);
    		set.setType("Quizlet Set");
    		break;
    	}
        	Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.close();

    }
    @FXML
      private void afsetClicked(){
    	isClicked = 1;
    	if(AFSet.isSelected())
    	{
    	Self.setDisable(true);
    	Quizlet.setDisable(true);
    	}
    else{
    	AFSet.setDisable(false);
    	Quizlet.setDisable(false);
    	Self.setDisable(false);
    }
    }
    	
    
    @FXML
    private void selfClicked(){
    	isClicked = 2;
    	if(Self.isSelected())
    	{
    	AFSet.setDisable(true);
    	Quizlet.setDisable(true);
    	}
    else{
    	AFSet.setDisable(false);
    	Quizlet.setDisable(false);
    	Self.setDisable(false);
    }
    }
    
    @FXML
    private void quizletClicked(){
    	isClicked = 3;
    	if(Quizlet.isSelected())
    	{
    	AFSet.setDisable(true);
    	Self.setDisable(true);
    	}
    else{
    	AFSet.setDisable(false);
    	Quizlet.setDisable(false);
    	Self.setDisable(false);
    }
    }
 }


