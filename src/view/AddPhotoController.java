package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.Album;
import util.Controller;
import util.Photo;
import util.User;

public class AddPhotoController extends Controller{
	private User currUser;
	private Album album;
	private File selectedFile;
	
	@FXML TextField photoPathTextField;
	@FXML TextField captionTextField;
	
	public void start (Stage primaryStage, User currUser, Album album) {
		this.primaryStage = primaryStage;
		this.currUser = currUser;
		this.album = album;
	}
	
	public void addPhoto () {
		if (selectedFile == null) {
			return;
		}
		if (selectedFile.exists()) {
			String caption = captionTextField.getText();
			if (!caption.isEmpty()) {
				album.addPhoto(new Photo (selectedFile, caption));
			}
			else {
				album.addPhoto(new Photo (selectedFile));
			}
			currUser.saveUser();
			
		}
	}
	public void getUserFile () {
		FileChooser fileChooser = new FileChooser();
		selectedFile = fileChooser.showOpenDialog(primaryStage);
		
		if (selectedFile != null) {
			photoPathTextField.setText(selectedFile.getAbsolutePath());
		}
	}
}
