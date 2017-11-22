package util;


import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Controller {
	protected Stage primaryStage;
	protected User user;
	protected ArrayList<Photo> result;
	
	public void start (Stage primaryStage, User user) {
		this.primaryStage = primaryStage;
		this.user = user;
		return;
	}
	
	public void start (Stage primaryStage, User user, ArrayList<Photo> result) {
		this.primaryStage = primaryStage;
		this.user = user;
		this.result = result;
		return;
	}
	
	public void start (Stage primaryStage) {
		this.primaryStage = primaryStage;
		return;
	}
	
	 public void errDialog(String emessage) {
		   Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(this.primaryStage);
			alert.setTitle("ALERT ERROR");
			alert.setHeaderText("ERROR");
			alert.setContentText(emessage);
			alert.showAndWait();
	   }
	public void closeWindow () {
		primaryStage.close();
	}
}
