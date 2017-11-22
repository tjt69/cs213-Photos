package view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.Album;
import util.Controller;
import util.Photo;
import util.StageManager;
import util.User;


public class Search_PhotoController extends Controller {
	private User currUser;
	
	private ObservableList<Photo> obsList;
	StageManager stageManager = new StageManager();
	
	@FXML ListView<Photo> display;
	@FXML TextField start_date;
	@FXML TextField end_date;
	@FXML TextField tag_type;
	@FXML TextField tag_value;
	@FXML Button search_date;
	@FXML Button create_album;
	@FXML Button back;
	@FXML Button search_tag;
	
	public void start(Stage primaryStage, User user) {
		this.primaryStage = primaryStage;
		this.currUser = user;
		DisplaySearchResults();
	}
	
	public void backButton(ActionEvent e) throws IOException{
		stageManager.loadScene(primaryStage, "Albums", currUser);
	}
	
	public void searchDateButton(ActionEvent e) throws IOException{
		obsList = FXCollections.observableArrayList();
		SimpleDateFormat f = new SimpleDateFormat("mm/dd/yy");
		Date startD = null;
		Date endD = null;
		
		if(start_date.getText().equals("")||end_date.getText().equals("")) {
			return;
		}
		try {
			startD = f.parse(start_date.getText());
			endD = f.parse(end_date.getText());
		} catch (ParseException e1) {
			errDialog("Error: Wrong Format\nRequired: MM/DD/YY",this.primaryStage);
			return;
		}
		
		if(startD!=null && endD!=null) {
			if (startD.compareTo(endD) > 0 || startD.equals(endD)){
				errDialog("Error: Start Date must be Prior to End Date",this.primaryStage);
				return;
			}
			for(Album i: currUser.getAlbums()) {
				for(Photo p: i.getPhotos()) {
					if (p.getDate().compareTo(startD) >= 0 && p.getDate().compareTo(endD) <= 0)
						obsList.add(p);
				}
			}
			if(obsList.isEmpty()) {
				errDialog("No Photos within specified date range",this.primaryStage);
			}else {
				DisplaySearchResults();
			}
		}
	}
	
	

	public void searchTagButton(ActionEvent e) throws IOException{
		
	}
	
	public void createAlbum(ActionEvent e) throws IOException{
		
	}
	
	private void DisplaySearchResults() {
		display.setItems(obsList);
		display.setCellFactory(param -> new ListCell<Photo>() {
			private ImageView imageView = new ImageView();
			@Override
			public void updateItem (Photo photo, boolean empty) {
				super.updateItem(photo, empty);
				if (empty) {
					setText (null);
					setGraphic(null);
				}
				else {
					String path = "file:///" + photo.getPath();
					Image image = new Image(path, 50, 50, true, true);
					imageView.setImage(image);
					setGraphic(imageView);
				}
			}
		});
		
	}
}

