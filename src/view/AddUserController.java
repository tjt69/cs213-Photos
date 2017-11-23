package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import util.Controller;
import util.User;

public class AddUserController extends Controller {
	@FXML Button addUserButton;
	@FXML Button cancelButton;
	@FXML TextField userNameTextField;
	@FXML TextField passwordTextField;
	
	public void start (Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	// Adds user if addUserButton is hit and the textFields are populated
	public void addUser () throws IOException {
		String userName = userNameTextField.getText();
		String password = passwordTextField.getText();
		
		// If the userName and password textfields are populated, write
		// the credentials into the accounts.txt file
		if (!userName.isEmpty() && !password.isEmpty()) {
			try {
				// Deserialize storedUsers data and add new User
				FileInputStream fileIn = new FileInputStream("accounts.dat");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				ArrayList<User> storedUsers = (ArrayList<User>) in.readObject();
				in.close();
				fileIn.close();
				storedUsers.add(new User(userName, password, "user"));
				
				// Serialize updated storedUsers
				FileOutputStream fileOut = new FileOutputStream("accounts.dat");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(storedUsers);
				out.close();
				fileOut.close();
				closeWindow();
			}
			catch (ClassNotFoundException ex) {
				System.out.println("Class not found.");
			}
			catch (IOException ex) {
				System.out.println("Error reading file.");
			}
		}
		else {
			errDialog("Please provide inputs for both fields"); 
		}
	}
	
}
