package view;

import java.io.FileInputStream;
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
import util.Photo;
import util.User;

public class AddAlbumController extends Controller{
	private User currUser;
	private ArrayList<Photo> result;
	@FXML TextField albumNameTextField;
	
	public void start(Stage primaryStage,User user) {
		this.currUser = user;
		this.primaryStage = primaryStage;
	}
	
	public void start(Stage primaryStage,User user, ArrayList<Photo> result) {
		this.currUser = user;
		this.primaryStage = primaryStage;
		this.result = result;
		
	}
	
	public void addAlbum () {
		String albumName = albumNameTextField.getText();
		if (!albumName.isEmpty()) {
			if(result==null) {
				Album album = new Album(albumName);
				currUser.addAlbums(album);
			}else {
				Album album = new Album(albumName,result);
				currUser.addAlbums(album);
			}
			
			
			try {
				// Deserialize storedUsers data
				FileInputStream fileIn = new FileInputStream("accounts.dat");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				ArrayList<User> storedUsers = (ArrayList<User>) in.readObject();
				in.close();
				fileIn.close();
				
				// Find currUser in storedUsers and add album to the user
				for (User user : storedUsers) {
					if (user.equals(currUser)) {
						storedUsers.set(storedUsers.indexOf(user), currUser);
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
		else {
			return;
		}
		closeWindow();
	}
}
