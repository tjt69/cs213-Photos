package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
		StageManager stageManager = new StageManager();
		stageManager.getStage("Add_User").showAndWait();
		displayUsers();
	}
	
	public void deleteUser (ActionEvent e) throws IOException {
		String selectedUser = usersListView.getSelectionModel().getSelectedItem();
		System.out.println(selectedUser);
		StageManager stageManager = new StageManager();
		stageManager.getStage("Delete").showAndWait();
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
