package util;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	
	 public void errDialog(String emessage, Stage s) {
		   Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(s);
			alert.setTitle("ALERT ERROR");
			alert.setHeaderText("ERROR");
			alert.setContentText(emessage);
			alert.showAndWait();
	   }
	public void closeWindow () {
		primaryStage.close();
	}
}
