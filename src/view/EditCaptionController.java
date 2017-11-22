package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Controller;
import util.Photo;
import util.User;

public class EditCaptionController extends Controller {
	private User currUser;
	private Photo selectedPhoto;	
	
	@FXML TextField captionTextField;
	
	public void start (Stage primaryStage, User currUser, Photo selectedPhoto) {
		this.primaryStage = primaryStage;
		this.currUser = currUser;
		this.selectedPhoto = selectedPhoto;
	}
	
	public void editCaption () {
		String caption = captionTextField.getText();
		selectedPhoto.setCaption(caption);
		currUser.saveUser();
		closeWindow();
	}
 }
