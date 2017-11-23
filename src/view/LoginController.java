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

/** 
 * Controls the "Login" stage
 * @author Travis Thiel
 * @author Justin Valeroso
 */
public class LoginController extends Controller{
	@FXML Button signInButton;
	@FXML Button quitButton;
	@FXML TextField userNameTextField;
	@FXML TextField passwordTextField;
	
	private ArrayList<User> users;
	
	/**
	 * Initializes controller's private fields and sets up controller
	 * for stage
	 * @param primaryStage is the Stage that this controller controls
	 */
	public void start(Stage primaryStage) {
		// This is so LoginConroller knows what the primaryStage is
		// Not too sure if this is 'hacky' :p
		this.primaryStage = primaryStage;
	}
	
	/**
	 * Checks to see if the User inputed valid credentials and loads the appropriate stage
	 * @param e the ActionEvent that prompted the button 
	 */
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
			boolean validUser = false;
			for (User user : users) {
				if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
					if (user.getAccountType().equals("user")) {
						StageManager stageManager = new StageManager();
						stageManager.loadScene(primaryStage, "Albums", user);
						validUser = true;
					}
					else if (user.getAccountType().equals("admin")) {
						StageManager stageManager = new StageManager();
						stageManager.loadScene(primaryStage, "Manage_Users");
						validUser = true;
						break;
					}
				}
			}
			if (!validUser) {
				errDialog("Invalid username/password");
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
