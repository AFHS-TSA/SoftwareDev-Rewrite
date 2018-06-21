package main.java.app.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import org.Quizlet;
import org.controlsfx.control.Notifications;
import org.json.JSONException;

import com.jfoenix.controls.JFXButton;
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
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.java.app.SpacedRep;
import main.java.app.Var;


public class QuizletSearchController implements Initializable {
	
	@FXML
	private TextField quizSearch;
    @FXML
    private JFXTreeTableView<Set> searchResults;
	ObservableList<Set> sets = FXCollections.observableArrayList();

    @FXML
    private BorderPane baseBorder;
	
	@SuppressWarnings("unchecked")
	@FXML
	public void onQuizSearch() throws Exception {

		JFXTreeTableColumn<Set, String> titleName = new JFXTreeTableColumn<>("Title");
		titleName.setPrefWidth(300);
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
		creatorName.setPrefWidth(220);
		creatorName.setCellValueFactory((TreeTableColumn.CellDataFeatures<Set, String> param) ->{
		    if(creatorName.validateValue(param)) return param.getValue().getValue().setCreator;
		    else return creatorName.getComputedValue(param);
		});	
		
		
		Search search = new Search();
		search.start();
		
		final TreeItem<Set> searchRoot = new RecursiveTreeItem<Set>(sets, RecursiveTreeObject::getChildren);
		searchResults.getColumns().setAll(titleName, termCount, creatorName);
		searchResults.setRoot(searchRoot);
		searchResults.setShowRoot(false);
	}
	
	@FXML
	public void onQuizImport() throws JSONException, Exception {
		Var.quizletSelectionID = Integer.parseInt(quizSearch.getText());
		Var.quizletSelectionTitle = Quizlet.getTitle(Var.clientID, Var.quizletSelectionID);
		
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Quizlet Set Import");
		alert.setHeaderText("Is this the correct Quizlet set?");
		alert.setContentText("You are about to import " + "'" + Var.quizletSelectionTitle + "'");

		Optional<ButtonType> option = alert.showAndWait();

		if (option.get() == ButtonType.OK) {
			Stage stage = (Stage) baseBorder.getScene().getWindow();
			stage.close();
		} else {}
	}
	
	@FXML
	public void onQuizHelp() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Quizlet Set Import");
		alert.setHeaderText("Confused on how to import a set?");
		alert.setContentText("Simply just copy and paste the setID of your Quizlet set. For Example  in https://quizlet.com/277994239/Set-Name/ the setID would be 277994239");
		alert.showAndWait();
	}
	
	@FXML
	public void onQuizClear() {
		sets.clear();
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
	
	class Search extends Thread {
		public void run() {
			try {
				String searchString = quizSearch.getText().replace(" ", "%20");
				Quizlet.setSearch(Var.clientID, searchString);
				int[] quizID = Quizlet.getSearchID();

				for(int id : quizID)
					sets.add(new Set(Quizlet.getTitle(Var.clientID, id), Integer.toString(Quizlet.getTermCount(Var.clientID, id)), Quizlet.getCreator(Var.clientID, id)));
				
			} catch (IOException | JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		searchResults.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<TreeItem>() {
		    @Override
		    public void onChanged(Change<? extends TreeItem> change) {
				try {
					int[] quizID = Quizlet.getSearchID();
					Var.quizletSelectionID = quizID[searchResults.getSelectionModel().getSelectedIndex()];
					try {
						Var.quizletSelectionTitle = Quizlet.getTitle(Var.clientID, Var.quizletSelectionID);
					} catch (Exception e) {e.printStackTrace();}
					
			        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			        alert.setTitle("Quizlet Set");
			        alert.setHeaderText("Are you sure you want to pick this set?");
			        alert.setContentText("You picked " + "'" + Var.quizletSelectionTitle + "'");

			        Optional<ButtonType> option = alert.showAndWait();
			        if (option.get() == ButtonType.OK) {
				        SpacedRep set = new SpacedRep();
				        set.setTitle(Var.quizletSelectionTitle);
				        set.setQuizlet(true);
				        set.setID(Quizlet.getSetID());
				        Var.studySets.add(set);
				        
				        Connection conn = null;
				        try {
				        	conn = DriverManager.getConnection(Var.sqlURL);
				            Statement statement = conn.createStatement();
				            System.out.println(Var.username);
				            statement.executeUpdate("insert into " + Var.username + " (studysets) values ('" + Var.quizletSelectionTitle + "')");
				            			            
				            
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
			        
			        
			        	Stage stage = (Stage) baseBorder.getScene().getWindow();
			            stage.close();
			        } else {}
		    	
				} catch (JSONException | IOException e) {
					e.printStackTrace();
				}
			}
		});	

	}
}
