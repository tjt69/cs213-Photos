package view;

import java.io.*;
import java.util.StringTokenizer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Album;
import util.Controller;
import util.StageManager;

public class AlbumsController extends Controller implements Serializable{
	private ObservableList<String> albums;
	
	@FXML ListView<Album> albumsListView;
	@FXML Button add;
	@FXML Button delete;
	@FXML Button edit;
	@FXML Button search;
	@FXML Button logout;
	@FXML Button quit;
	@FXML TextField albumName;
	@FXML TextField numPhotos;
	@FXML TextField dateRange;
	
	public void start(Stage primaryStage) {
		displayAlbums();
		this.primaryStage = primaryStage;
	}

	public void addAlbum (ActionEvent e) throws IOException {
		// Open a second window that prompts the user for new user's credentials
		StageManager stageManager = new StageManager();
		stageManager.getStage("Add_Album").showAndWait();
		
		// After adding a user, re-display to update view
		displayAlbums();
	}
	
	public void deleteAlbum(ActionEvent e) throws IOException{
		
	}
	
	public void editAlbum(ActionEvent e) throws IOException{
		
	}
	
	public void searchAlbum(ActionEvent e) throws IOException{
		
	}
	
	public void logOut (ActionEvent e) throws IOException {
		StageManager stageManager = new StageManager();
		stageManager.loadScene(primaryStage, "Login");
	}
	
	
	
	
	private void displayAlbums() {
		albums = FXCollections.observableArrayList();
		
		
		
	}
	
	
	
}
