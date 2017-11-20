package view;

import java.io.*;
import java.util.StringTokenizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import util.Controller;
import util.StageManager;

public class LoginController extends Controller{
	@FXML Button signInButton;
	@FXML Button quitButton;
	@FXML TextField userNameTextField;
	@FXML TextField passwordTextField;
	
	public void start(Stage primaryStage) {
		// This is so LoginConroller knows what the primaryStage is
		// Not too sure if this is 'hacky' :p
		this.primaryStage = primaryStage;
	}
	
	// If 'signIn' is pressed, check the sign in to see if the information is valid
	public void checkSignIn (ActionEvent e) throws IOException {
		// Get username/password combo from TextFields
		String userName = userNameTextField.getText();
		String password = passwordTextField.getText();
		
		// Parse through user data in "accounts.txt" and see if there are any matches
		String line = "";
		String storedUserName, storedPassword, accountType;
		try {
			FileReader fileReader = new FileReader("accounts.txt");
			BufferedReader br = new BufferedReader(fileReader);
			while ((line = br.readLine()) != null) {
				StringTokenizer tk = new StringTokenizer(line);	
				storedUserName = tk.nextToken();
				storedPassword = tk.nextToken();
				if (storedUserName.equals(userName) && storedPassword.equals(password)) {
					accountType = tk.nextToken();
					if (accountType.equals("user")) {
						StageManager stageManager = new StageManager();
						stageManager.loadScene(primaryStage, "");
					}
					else if (accountType.equals("admin")) {
						StageManager stageManager = new StageManager();
						stageManager.loadScene(primaryStage, "Manage_Users");
					}
				}
			}
			br.close();
		}
		catch (FileNotFoundException ex) {
			System.out.println("File not found.");
		}
		catch (IOException ex) {
			System.out.println("Error reading file.");
		}
	}
}
