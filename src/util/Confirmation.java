package util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Confirmation {
	private boolean answer = false;
	
	public boolean confirmationAlert () throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/FXMLDocs/Delete.fxml"));
		Parent root = (Parent)loader.load();
		Stage deleteWindow = new Stage();
		
		// Set up the deleteWindow
		Scene scene = new Scene(root);
		deleteWindow.setScene(scene);
		deleteWindow.setResizable(false);
		
		// Find the buttons in Delete.fxml
		Button yesButton = (Button) scene.lookup("#yesButton");
		Button noButton = (Button) scene.lookup("#noButton");
		
		// Give buttons ActionEvents to change
		yesButton.setOnAction(e -> {
			answer = true;
			deleteWindow.close();
		});
		noButton.setOnAction(e -> {
			answer = false;
			deleteWindow.close();
		});
		deleteWindow.showAndWait();
		return answer;
	}
}
