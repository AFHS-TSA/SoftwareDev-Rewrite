package main.java.app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.Quizlet;
import org.json.JSONException;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import main.java.app.Var;


public class NewStudySetController implements Initializable {
	
	@FXML
	private TextField quizSearch;

    @FXML
    private JFXTreeTableView<Set> searchResults;
	
	@SuppressWarnings("unchecked")
	@FXML
	public void onQuizSearch() throws Exception {
		String searchString = quizSearch.getText().replace(" ", "%20");
		
		JFXTreeTableColumn<Set, String> titleName = new JFXTreeTableColumn<>("Title");
		titleName.setPrefWidth(250);
		titleName.setCellValueFactory((TreeTableColumn.CellDataFeatures<Set, String> param) ->{
		    if(titleName.validateValue(param)) return param.getValue().getValue().setTitle;
		    else return titleName.getComputedValue(param);
		});	
		
		JFXTreeTableColumn<Set, String> termCount = new JFXTreeTableColumn<>("Term Count");
		termCount.setPrefWidth(100);
		termCount.setCellValueFactory((TreeTableColumn.CellDataFeatures<Set, String> param) ->{
		    if(termCount.validateValue(param)) return param.getValue().getValue().termCount;
		    else return termCount.getComputedValue(param);
		});	
		
		JFXTreeTableColumn<Set, String> creatorName = new JFXTreeTableColumn<>("Creator");
		creatorName.setPrefWidth(150);
		creatorName.setCellValueFactory((TreeTableColumn.CellDataFeatures<Set, String> param) ->{
		    if(creatorName.validateValue(param)) return param.getValue().getValue().setCreator;
		    else return creatorName.getComputedValue(param);
		});	
		
		ObservableList<Set> sets = FXCollections.observableArrayList();
		
		Quizlet.setSearch(Var.clientID, searchString);
		int[] quizID = Quizlet.getSearchID();

		for(int id : quizID)
			sets.add(new Set(Quizlet.getTitle(Var.clientID, id), Integer.toString(Quizlet.getTermCount(Var.clientID, id)), Quizlet.getCreator(Var.clientID, id)));
			
		final TreeItem<Set> searchRoot = new RecursiveTreeItem<Set>(sets, RecursiveTreeObject::getChildren);
		searchResults.getColumns().setAll(titleName, termCount, creatorName);
		searchResults.setRoot(searchRoot);
		searchResults.setShowRoot(false);
	}
	
	class Set extends RecursiveTreeObject<Set>{
	    StringProperty setTitle;
	    StringProperty termCount;
	    StringProperty setCreator;
	 
	    public Set(String setTitle, String termCount, String setCreator) {
	        this.setTitle = new SimpleStringProperty(setTitle) ;
	        this.termCount = new SimpleStringProperty(termCount);
	        this.setCreator = new SimpleStringProperty(setCreator);
	    }
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		searchResults.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<TreeItem>() {
		    @Override
		    public void onChanged(Change<? extends TreeItem> change) {
				try {
					int[] quizID = Quizlet.getSearchID();
					System.out.println(quizID[searchResults.getSelectionModel().getSelectedIndex()]);
					
				} catch (JSONException | IOException e) {
					e.printStackTrace();
				}
			}
		});		
	}
}
