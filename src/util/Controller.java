package util;


import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Class Representation of a Controller object
 * @author Justin Valeroso
 * @author Travis Thiel
 *
 */
public class Controller {
	protected Stage primaryStage;
	protected User user;
	protected ArrayList<Photo> result;
	
	/**
	 * Initializes controller's private fields and sets up controller
	 * for stage
	 * @param primaryStage is the Stage that this controller controls
	 * @param user is the current User that's accessing this stage
	 */
	public void start (Stage primaryStage, User user) {
		this.primaryStage = primaryStage;
		this.user = user;
		return;
	}
	
	/**
	 * Initializes controller's private fields and sets up controller
	 * for stage
	 * @param primaryStage is the Stage that this controller controls
	 * @param user is the current User that's accessing this stage
	 * @param result is the ArrayList of Photos that are passed through this controller
	 * that will be used to populate the added Album
	 */	
	public void start (Stage primaryStage, User user, ArrayList<Photo> result) {
		this.primaryStage = primaryStage;
		this.user = user;
		this.result = result;
		return;
	}
	
	/**
	 * Initializes controller's private fields and sets up controller
	 * for stage
	 * @param primaryStage is the Stage that this controller controls
	 */
	public void start (Stage primaryStage) {
		this.primaryStage = primaryStage;
		return;
	}
	
	/**
	 * Alerts the user that an Error has occurred
	 * @param emessage String containing the error message
	 */
	 public void errDialog(String emessage) {
		   Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(this.primaryStage);
			alert.setTitle("ALERT ERROR");
			alert.setHeaderText("ERROR");
			alert.setContentText(emessage);
			alert.showAndWait();
	}
	 
	/**
	 * Closes the current Window
	 */
	public void closeWindow () {
		primaryStage.close();
	}
}
