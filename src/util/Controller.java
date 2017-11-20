package util;

import javafx.stage.Stage;

public class Controller {
	protected Stage primaryStage;
	
	public void start (Stage primaryStage) {
		this.primaryStage = primaryStage;
		return;
	}
	
	// Closes window when the cancelButton is hit
	public void closeWindow () {
		primaryStage.close();
	}
}
