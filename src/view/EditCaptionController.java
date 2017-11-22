package view;

import javafx.stage.Stage;
import util.Controller;
import util.Photo;
import util.User;

public class EditCaptionController extends Controller {
	private User currUser;
	private Photo selectedPhoto;	
	
	public void start (Stage primaryStage, User currUser, Photo selectedPhoto) {
		this.primaryStage = primaryStage;
		this.currUser = currUser;
		this.selectedPhoto = selectedPhoto;
	}
}
