package util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Helper class that holds methods to create Stages dynamically
public class StageManager {
	
	// Creates stage based off of input String sceneName
	public Stage getStage (String sceneName) throws IOException {
		// Set up loginController
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/FXMLDocs/" + sceneName +".fxml"));
		Parent root = (Parent)loader.load();
		Controller controller = loader.getController();
				
		Stage secondaryStage = new Stage();
		controller.start(secondaryStage);
								
		// Set up secondaryStage
		Scene scene = new Scene(root);
		secondaryStage.setScene(scene);
		secondaryStage.setTitle("Photo App");
		secondaryStage.setResizable(false);
				
		return secondaryStage;
	}
	
	// Loads a scene onto a stage
	public void loadScene (Stage primaryStage, String sceneName) throws IOException {
		// Set up loginController
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/FXMLDocs/" + sceneName + ".fxml"));
		Parent root = (Parent)loader.load();
		Controller controller = loader.getController();
		controller.start(primaryStage);
				
		// Set up primaryStage
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Photo App");
		primaryStage.setResizable(false); 
		primaryStage.show();		
	}
}
