package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Controller;
import util.Photo;
import util.User;

/** 
 * Controls the "Edit_Caption" stage
 * @author Travis Thiel
 * @author Justin Valeroso
 */
public class EditCaptionController extends Controller {
	private User currUser;
	private Photo selectedPhoto;	
	
	@FXML TextField captionTextField;
	
	/**
	 * Initializes controller's private fields and sets up controller
	 * for stage
	 * @param primaryStage is the Stage that this controller controls
	 * @param currUser is the current User that's accessing this stage
	 * @param selectedPhoto is the photo that is getting its caption editted
	 */
	public void start (Stage primaryStage, User currUser, Photo selectedPhoto) {
		this.primaryStage = primaryStage;
		this.currUser = currUser;
		this.selectedPhoto = selectedPhoto;
	}
	
	/**
	 * Edits the caption of the selectedPhoto based off of the User's input
	 */
	public void editCaption () {
		String caption = captionTextField.getText();
		selectedPhoto.setCaption(caption);
		currUser.saveUser();
		closeWindow();
	}
 }
