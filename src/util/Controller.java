package util;

import javafx.stage.Stage;

public class Controller {
	protected Stage primaryStage;
	protected User user;
	
	public void start (Stage primaryStage, User user) {
		this.primaryStage = primaryStage;
		this.user = user;
		return;
	}
	
	public void start (Stage primaryStage) {
		this.primaryStage = primaryStage;
		return;
	}
	
	public void closeWindow () {
		primaryStage.close();
	}
}
