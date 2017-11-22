package view;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import util.Album;
import util.Controller;
import util.Photo;
import util.User;

public class MovePhotoController extends Controller{
	private User currUser;
	private Album album;
	private Photo selectedPhoto;
	private ObservableList<Album> obsList;
	
	@FXML ListView<Album> albumsListView;
	
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
	
	public void start (Stage primaryStage, User currUser, Album album, Photo selectedPhoto) {
		this.primaryStage = primaryStage;
		this.currUser = currUser;
		this.album = album;
		this.selectedPhoto = selectedPhoto;
		
		displayAlbums();
	}
	
	public void movePhoto () {
		Album selectedAlbum = albumsListView.getSelectionModel().getSelectedItem();
		if (selectedAlbum == null) {
			return;
		}
		
		album.deletePhoto(selectedPhoto);
		selectedAlbum.addPhoto(selectedPhoto);
		
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
		}
		catch (ClassNotFoundException ex) {
			System.out.println("Class not found.");
		}
		catch (IOException ex) {
			System.out.println("Error reading file.");
		}
		
		closeWindow();
	}
	
	private void displayAlbums () {
		obsList = FXCollections.observableArrayList();
		for (Album album : currUser.getAlbums()) {
			if (album != this.album) {
				obsList.add(album);
			}
		}
		
		// Sort obsList
		FXCollections.sort(obsList, AlbumComparator);
		
		albumsListView.setItems(obsList);
		albumsListView.setCellFactory(param -> new ListCell<Album>() {
			@Override
			public void updateItem (Album album, boolean empty) {
				super.updateItem(album, empty);
				if (empty) {
					setText (null);
				}
				else {
					setText (album.getName());
				}
			}
		});		
	}
}
