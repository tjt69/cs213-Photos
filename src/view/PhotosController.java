package view;

import java.io.IOException;

import javafx.stage.Stage;
import util.Album;
import util.Controller;
import util.StageManager;
import util.User;

public class PhotosController extends Controller{
	private User currUser;
	private Album album;
	
	StageManager stageManager = new StageManager();
	public void start (Stage primaryStage, User currUser, Album selectedAlbum) {
		this.primaryStage = primaryStage;
		this.currUser = currUser;
		this.album = selectedAlbum;
	}
	
	public void addPhoto () throws IOException {
		stageManager.getAddPhotoStage(currUser, album).showAndWait();
	}
	
	public void goBack () throws IOException {
		stageManager.loadScene(primaryStage, "Albums", currUser);
	}
}
