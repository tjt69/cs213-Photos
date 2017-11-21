package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import util.Controller;
import util.StageManager;

public class ManageUsersController extends Controller{
	private ObservableList<String> users;
	
	@FXML ListView<String> usersListView;
	@FXML Button addButton;
	@FXML Button deleteButton;
	@FXML Button logOutButton;
	@FXML Button quitButton;
	
	public void start(Stage primaryStage) {
		displayUsers();
		this.primaryStage = primaryStage;
	}
	
	public void addUser (ActionEvent e) throws IOException {
		// Open a second window that prompts the user for new user's credentials
		StageManager stageManager = new StageManager();
		stageManager.getStage("Add_User").showAndWait();
		
		// After adding a user, re-display to update view
		displayUsers();
	}
	
	public void deleteUser (ActionEvent e) throws IOException {
		// Get the selected user's name and check if anything was selected
		String selectedUser = usersListView.getSelectionModel().getSelectedItem();
		if (selectedUser == null) {
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
				String line = "";
				String userName;
				
				File currentFile = new File("accounts.txt");
				File tempFile = new File("temp.txt");
				
				FileReader fileReader = new FileReader(currentFile);
				FileWriter fileWriter = new FileWriter(tempFile);
				
				BufferedReader br = new BufferedReader(fileReader);
				BufferedWriter bw = new BufferedWriter(fileWriter);
				
				while ((line = br.readLine()) != null) {
					StringTokenizer tk = new StringTokenizer(line);	
					userName = tk.nextToken();
					if (!selectedUser.equals(userName)) {
						bw.write(line + System.getProperty("line.separator"));
					}
				}
				
				br.close();
				bw.close();
				
				currentFile.delete();
				tempFile.renameTo(new File("accounts.txt"));
			}
			catch (FileNotFoundException ex) {
				System.out.println("File not found.");
			}
			catch (IOException ex) {
				System.out.println("Error reading file.");
			}
		}
		
		// After deleting a user, re-display to update view
		displayUsers();
	}
	
	public void logOut (ActionEvent e) throws IOException {
		StageManager stageManager = new StageManager();
		stageManager.loadScene(primaryStage, "Login");
	}
	
	// Helper method to display the list of users
	private void displayUsers () {
		// Create a new ObserableList
		users = FXCollections.observableArrayList();; 
		
		// Read "accounts.txt" file and add users to ObservableList
		String line = "";
		String userName, accountType;
		try {
			FileReader fileReader = new FileReader("accounts.txt");
			BufferedReader br = new BufferedReader(fileReader);
			while ((line = br.readLine()) != null) {
				StringTokenizer tk = new StringTokenizer(line);	
				userName = tk.nextToken();
				tk.nextToken();
				accountType = tk.nextToken();
				if (accountType.equals("user")) {
					users.add(userName);
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
		
		// Display ObservableList users in usersListView
		usersListView.setItems(users);
	}
}
