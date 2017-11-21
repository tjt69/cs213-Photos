package view;

import java.io.*;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import util.Controller;
import util.StageManager;
import util.User;

public class LoginController extends Controller{
	@FXML Button signInButton;
	@FXML Button quitButton;
	@FXML TextField userNameTextField;
	@FXML TextField passwordTextField;
	
	private ArrayList<User> users;
	
	public void start(Stage primaryStage) {
		// This is so LoginConroller knows what the primaryStage is
		// Not too sure if this is 'hacky' :p
		this.primaryStage = primaryStage;
	}
	
	// If 'signIn' is pressed, check the sign in to see if the information is valid
	public void checkSignIn (ActionEvent e)  {
		// Get username/password combo from TextFields
		String userName = userNameTextField.getText();
		String password = passwordTextField.getText();
		
		// Parse through user data in "accounts.txt" and see if there are any matches
		User storedUser;
		try {
			FileInputStream fileIn = new FileInputStream("accounts.dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			users = (ArrayList<User>) in.readObject();
			for (User user : users) {
				if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
					if (user.getAccountType().equals("user")) {
						StageManager stageManager = new StageManager();
						stageManager.loadScene(primaryStage, "Albums");
					}
					else if (user.getAccountType().equals("admin")) {
						StageManager stageManager = new StageManager();
						stageManager.loadScene(primaryStage, "Manage_Users");
						break;
					}
				}
			}
			in.close();
		}
		catch (ClassNotFoundException ex) {
			System.out.println("Class not found.");
		}
		catch (IOException ex) {
			System.out.println(ex);
		}
	}
}
