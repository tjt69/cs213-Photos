package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import util.Controller;
import util.StageManager;
import util.User;

public class ManageUsersController extends Controller{
	private ObservableList<User> users;
	
	@FXML ListView<User> usersListView;
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
		User selectedUser = usersListView.getSelectionModel().getSelectedItem();
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
				// Deserialize storedUsers data
				FileInputStream fileIn = new FileInputStream("accounts.dat");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				ArrayList<User> storedUsers = (ArrayList<User>) in.readObject();
				in.close();
				fileIn.close();
				
				// Traverse storedUsers and remove user that has same credentials as selectedUser
				for (User user : storedUsers) {
					if (user.getUserName().equals(selectedUser.getUserName()) &&
						user.getPassword().equals(selectedUser.getPassword())) {
						storedUsers.remove(user);
						break;
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
		users = FXCollections.observableArrayList();

		// Read "accounts.txt" file and add users to ObservableList
		try {
			FileInputStream fileIn = new FileInputStream("accounts.dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			ArrayList<User> storedUsers = (ArrayList<User>) in.readObject();
			for (User user : storedUsers) {
				if (user.getAccountType().equals("user")) {
					users.add(user);
				}
			}
			in.close();
		}
		catch (FileNotFoundException ex) {
			System.out.println("File not found.");
		}
		catch (IOException ex) {
			System.out.println("Error reading file.");
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Class not found.");
		}
		
		// Display ObservableList users in usersListView
		usersListView.setItems(users);
		usersListView.setCellFactory(new Callback<ListView<User>, ListCell<User>>(){  
            @Override
            public ListCell<User> call(ListView<User> p) {                 
                ListCell<User> cell = new ListCell<User>(){ 
                    @Override
                    protected void updateItem(User user, boolean bln) {
                        super.updateItem(user, bln);
                        if (user != null) {
                            setText(user.getUserName());
                        }
                        else {
                        	setText(null);
                        }
                    }
 
                };
                 
                return cell;
            }
        });
	}
}
