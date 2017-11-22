package view;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import util.Album;
import util.Controller;
import util.StageManager;
import util.User;

public class AlbumsController extends Controller implements Serializable{

	private static final long serialVersionUID = 1L;

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
	
	private User currUser;
	private ObservableList<Album> obsList;
	private int num;
	
	public static Comparator<Album> AlbumComparator = new Comparator<Album>() {
		public int compare(Album a1, Album a2) {
			if(a1.getName().compareToIgnoreCase(a2.getName())==0) {
				if(a1.getNewestDateString().compareToIgnoreCase(a2.getNewestDateString())==0){
					return a2.getOldestDateString().compareToIgnoreCase(a1.getOldestDateString());
				}else {	
					return a1.getNewestDateString().compareToIgnoreCase(a2.getNewestDateString());
				}
			}else {
				return a1.getName().compareToIgnoreCase(a2.getName());
			}
		}

	};
	
	public void start(Stage primaryStage,User user) {
		this.currUser = user;
		this.primaryStage = primaryStage;
		displayAlbums();
	}

	public void addAlbum (ActionEvent e) throws IOException {
		// Open a second window that prompts the user for new album's credentials
		StageManager stageManager = new StageManager();
		stageManager.getStage("Add_Album",currUser).showAndWait();
		
		displayAlbums();
	}
	
	public void deleteAlbum(ActionEvent e) throws IOException{
		// Get the selected album's name and check if anything was selected
		Album selectedAlbum = albumsListView.getSelectionModel().getSelectedItem();
		if (selectedAlbum == null) {
			System.out.println("nothing was selected.");
			return;
		}
				
		// Ask the user if they really want to delete the selected user
		StageManager stageManager = new StageManager();
				
		// If user confirms, copy the entire file into a temp file
		// but don't copy the line that matches the selectedUser.
		// This mimics "deleting" a user from the accounts.txt file.				
		if (stageManager.getConfirmation()) {
			try {
				// Deserialize storedUsers data
				FileInputStream fileIn = new FileInputStream("accounts.dat");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				ArrayList<User> storedUsers = (ArrayList<User>) in.readObject();
				in.close();
				fileIn.close();
						
				// Traverse storedUsers and remove selected album
				for (User u : storedUsers) {
					System.out.println("User: "+u.getUserName()+" Curr: "+currUser.getUserName());
					if (currUser.equals(u)) {
						currUser.deleteAlbum(selectedAlbum);
						storedUsers.set(storedUsers.indexOf(u), currUser);
					}
				}
						
				// Serialize updated storedUsers
				FileOutputStream fileOut = new FileOutputStream("accounts.dat");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(storedUsers);
				out.close();
				fileOut.close();
			}
			catch (ClassNotFoundException ex) {
				System.out.println("Class not found.");
			}
			catch (IOException ex) {
				System.out.println("Error reading file.");
			}
		}
		
		displayAlbums();

	}
	
	public void editAlbum(ActionEvent e) throws IOException{
		Album selectedAlbum = albumsListView.getSelectionModel().getSelectedItem();
		
		// Loads a scene onto a stage
		// Set up EditAlbumController
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/FXMLDocs/Edit_Album.fxml"));
		Parent root = (Parent)loader.load();
		EditAlbumController controller = loader.getController();
		
		Stage secondaryStage = new Stage();
		controller.start(secondaryStage, currUser, selectedAlbum);
		
		// Set up secondaryStage
		Scene scene = new Scene(root);
		secondaryStage.setScene(scene);
		secondaryStage.setTitle("Photo App");
		secondaryStage.setResizable(false); 
		secondaryStage.showAndWait();
		
		// After prompting user for edit, re-display updated albums
		displayAlbums();
	}
	
	public void searchAlbum(ActionEvent e) throws IOException{
		
	}
	
	public void logOut (ActionEvent e) throws IOException {
		StageManager stageManager = new StageManager();
		stageManager.loadScene(primaryStage, "Login");
	}
	
	private void displayAlbums () {
		obsList = FXCollections.observableArrayList();
		
//		if(obsList.isEmpty()) {
//			Album stock = new Album("Stock");
//			System.out.println(stock.getName());
//			currUser.addAlbums(stock);
//			obsList.add(stock);
//		}
		
		// Add all of user's albums to obsList
		for (Album album : currUser.getAlbums()) {
			obsList.add(album);
		}
		
		// Sort obsList
		FXCollections.sort(obsList, AlbumComparator);
		
		// Display currUser's albums
		albumsListView.setItems(obsList);
		albumsListView.getSelectionModel().select(0);
		
		albumsListView
      	.getSelectionModel()
      		.selectedIndexProperty()
      			.addListener(
           (obs, oldVal, newVal) -> 
               num = albumsListView.getSelectionModel().getSelectedIndex());
      
		albumsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Album>() {

    	    @Override
    	    public void changed(ObservableValue<? extends Album> observable, Album oldValue, Album newValue) {
    	    	if(newValue!=null) {
    	    		albumName.setText("Album Name: \t"+"\""+newValue.getName()+"\"");
    	    		numPhotos.setText("# of Photos: \t"+newValue.getNumPhoto());
    	    		dateRange.setText("Date Range: \t"+newValue.getOldestDateString()+" - "+newValue.getNewestDateString());
    	    	}
    	    }
    	});         		
		
		albumsListView.setCellFactory(new Callback<ListView<Album>, ListCell<Album>>(){
    	  
            @Override
            public ListCell<Album> call(ListView<Album> p) {
                 
                ListCell<Album> cell = new ListCell<Album>(){
 
                    @Override
                    protected void updateItem(Album a, boolean bln) {
                        super.updateItem(a, bln);
                        if (a != null) {
                            setText(a.getName());
                            albumName.setText("Album Name: \t"+"\""+a.getName()+"\"");
            	    		numPhotos.setText("# of Photos: \t"+a.getNumPhoto());
            	    		dateRange.setText("Date Range: \t"+a.getOldestDateString()+" - "+a.getNewestDateString());
                        }
                        else {
                        	setText(null);
                        }
                    }
 
                };
                 
                return cell;
            }
        });		
		
		// Add a double-click handler so that when an album is double-clicked,
		// the Photos scene is loaded
		albumsListView.setOnMouseClicked (new EventHandler<MouseEvent>() {
			@Override
			public void handle (MouseEvent click) {
				Album selectedAlbum = albumsListView.getSelectionModel().getSelectedItem();
				if (click.getClickCount() == 2) {
					StageManager stageManager = new StageManager();
					try {
						stageManager.loadPhotosScene(primaryStage, currUser, selectedAlbum);
					} 
					catch (IOException ex) {
						System.out.println(ex);
					}
				}
			}
		});
	}
	
}
