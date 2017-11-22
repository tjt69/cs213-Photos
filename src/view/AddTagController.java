package view;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Album;
import util.Controller;
import util.Photo;
import util.Tag;
import util.User;

public class AddTagController extends Controller {
	private User currUser;
	private Photo selectedPhoto;
	
	@FXML TextField tagTypeTextField;
	@FXML TextField tagValueTextField;
	
	public void start (Stage primaryStage, User currUser, Photo selectedPhoto) {
		this.primaryStage = primaryStage;
		this.currUser = currUser;
		this.selectedPhoto = selectedPhoto;
	}
	
	public void addTag () {
		String tagType = tagTypeTextField.getText();
		String tagValue = tagValueTextField.getText();
		
		if (!tagType.isEmpty() && !tagValue.isEmpty()) {
			Tag tag = new Tag(tagType, tagValue);
			selectedPhoto.addTag(tag);
			currUser.saveUser();
			closeWindow();
		}
	}
	
}
