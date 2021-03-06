package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Album;
import util.Controller;
import util.User;

/** 
 * Controls the "Edit_Album" stage
 * @author Travis Thiel
 * @author Justin Valeroso
 */
public class EditAlbumController extends Controller{
	private User currUser;
	private Album selectedAlbum;
	
	@FXML TextField albumNameTextField;
	
	/**
	 * Initializes controller's private fields and sets up controller
	 * for stage
	 * @param primaryStage is the Stage that this controller controls
	 * @param user is the current User that's accessing this stage
	 * @param selectedAlbum is the album that will be editted
	 */
	public void start (Stage primaryStage, User currUser, Album selectedAlbum) {
		this.primaryStage = primaryStage;
		this.currUser = currUser;
		this.selectedAlbum = selectedAlbum;
	}
	
	/**
	 * Edits the album's name based off what the User inputed as the new name
	 */
	public void editAlbum () {
		String albumName = albumNameTextField.getText();
		selectedAlbum.setName(albumName);
		for (Album album : currUser.getAlbums()) {
			if (album.getName().equals(albumName)) {
				errDialog("Cannot have two albums with the same name.");
			}
		}
		
		try {
			// Deserialize storedUsers data and add new User
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
			System.out.println(ex);
		} 
		catch (FileNotFoundException ex) {
			System.out.println(ex);
		} 
		catch (IOException ex) {
			System.out.println(ex);
		}
		
		closeWindow();
	}
}
