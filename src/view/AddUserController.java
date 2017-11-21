package view;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import util.Controller;

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
				FileWriter fileWriter = new FileWriter("accounts.txt", true);
				BufferedWriter bw = new BufferedWriter(fileWriter);
				bw.write(userName + " " + password + " user");
				bw.newLine();
				bw.close();
				closeWindow();
			}
			catch (FileNotFoundException ex) {
				System.out.println("File not found.");
			}
			catch (IOException ex) {
				System.out.println("Error reading file.");
			}
		}
	}
	
}
