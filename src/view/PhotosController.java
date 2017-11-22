package view;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.Album;
import util.Controller;
import util.Photo;
import util.StageManager;
import util.User;

public class PhotosController extends Controller{
	private User currUser;
	private Album album;
	private ObservableList<Photo> obsList;
	
	@FXML ListView<Photo> photosListView;
	
	StageManager stageManager = new StageManager();
	
	public void start (Stage primaryStage, User currUser, Album selectedAlbum) {
		this.primaryStage = primaryStage;
		this.currUser = currUser;
		this.album = selectedAlbum;
		
		displayPhotos();
	}
	
	public void addPhoto () throws IOException {
		stageManager.getAddPhotoStage(currUser, album).showAndWait();
		displayPhotos();
	}
	
	public void deletePhoto () throws IOException {
		Photo selectedPhoto = photosListView.getSelectionModel().getSelectedItem();
		if (selectedPhoto == null) {
			return;
		}
		
		if (stageManager.getConfirmation()) {
			album.deletePhoto(selectedPhoto);
			try {
				// Deserialize storedUsers data
				FileInputStream fileIn = new FileInputStream("accounts.dat");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				ArrayList<User> storedUsers = (ArrayList<User>) in.readObject();
				in.close();
				fileIn.close();
						
				// Traverse storedUsers and remove selected album
				for (User u : storedUsers) {
					if (currUser.equals(u)) {
						storedUsers.set(storedUsers.indexOf(u), currUser);
					}
				}
						
				// Serialize updated storedUsers
				FileOutputStream fileOut = new FileOutputStream("accounts.dat");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(storedUsers);
				out.close();
				fileOut.close();
				
				displayPhotos();
			}
			catch (ClassNotFoundException ex) {
				System.out.println("Class not found.");
			}
			catch (IOException ex) {
				System.out.println("Error reading file.");
			}			
			
		}
		
	}
	
	public void goBack () throws IOException {
		stageManager.loadScene(primaryStage, "Albums", currUser);
	}
	
	private void displayPhotos () {
		ArrayList<Photo> photos = album.getPhotos();
		obsList = FXCollections.observableArrayList();
		
		for (Photo photo : photos) {
			obsList.add(photo);
		}
		
		photosListView.setItems(obsList);
		photosListView.setCellFactory(param -> new ListCell<Photo>() {
			private ImageView imageView = new ImageView();
			@Override
			public void updateItem (Photo photo, boolean empty) {
				super.updateItem(photo, empty);
				if (empty) {
					setText (null);
					setGraphic(null);
				}
				else {
					String path = "file:///" + photo.getPath();
					Image image = new Image(path, 50, 50, true, true);
					imageView.setImage(image);
					setGraphic(imageView);
				}
			}
		});
	}
}
