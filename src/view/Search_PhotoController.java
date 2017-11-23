package view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import util.Tag;
import util.User;

/** 
 * Controls the "Search_Photo" stage
 * @author Travis Thiel
 * @author Justin Valeroso
 */
public class Search_PhotoController extends Controller {
	private User currUser;
	
	private ObservableList<Photo> obsList;
	private ObservableList<Tag> tagsObsList;
	StageManager stageManager = new StageManager();
	
	@FXML ListView<Photo> display;
	@FXML ListView<Tag> tagListView;
	@FXML ListView<Tag> para_ListView;
	@FXML ImageView image_view;
	@FXML TextField caption;
	@FXML TextField date;
	
	@FXML TextField start_date;
	@FXML TextField end_date;
	@FXML TextField tag_type;
	@FXML TextField tag_value;
	@FXML Button search_date;
	@FXML Button create_album;
	@FXML Button back;
	@FXML Button search_tag;
	@FXML Button remove;
	
	/**
	 * Initializes controller's private fields and sets up controller
	 * for stage
	 * @param primaryStage is the Stage that this controller controls
	 * @param currUser is the current User that's accessing this stage
	 */
	public void start(Stage primaryStage, User user) {
		this.primaryStage = primaryStage;
		this.currUser = user;
		DisplaySearchResults();
	}
	
	/**
	 * Takes the User back to the "Albums" stage
	 * @param e the ActionEvent that prompted the button	
	 * @throws IOException
	 */
	public void backButton(ActionEvent e) throws IOException{
		stageManager.loadScene(primaryStage, "Albums", currUser);
	}
	
	/**
	 * Populates the ListView based off of the User's search parameters
	 * @param e the ActionEvent that prompted the button
	 * @throws IOException
	 */
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
			errDialog("Error: Wrong Format\nRequired: MM/DD/YY");
			return;
		}
		
		if(startD!=null && endD!=null) {
			if (startD.compareTo(endD) > 0 || startD.equals(endD)){
				errDialog("Error: Start Date must be Prior to End Date");
				return;
			}
			for(Album i: currUser.getAlbums()) {
				for(Photo p: i.getPhotos()) {
					if (p.getDate().compareTo(startD) >= 0 && p.getDate().compareTo(endD) <= 0)
						obsList.add(p);
				}
			}
			if(obsList.isEmpty()) {
				errDialog("No Photos within specified date range");
			}else {
				DisplaySearchResults();
			}
		}
	}
	
	/**
	 * Populates the ListView based off of the User's search parameters
	 * @param e the ActionEvent that prompted the button
	 * @throws IOException
	 */
	public void searchTagButton(ActionEvent e) throws IOException{
		obsList = FXCollections.observableArrayList();
		String t = tag_type.getText();
		String v = tag_value.getText();
		
		if(t.equals("")&&v.equals("")) {
			return;
		}
		Tag tag = new Tag(t,v);
		for(Album i: currUser.getAlbums()) {
				for(Photo p: i.getPhotos()) {
					if(p.containsTag(tag)) {
						obsList.add(p);
					}
					
				}
		}
		if(obsList.isEmpty()) {
			errDialog("No Photos containing specified tag");
		}else {
			DisplaySearchResults();
		}

		
	}
	
	/**
	 * Creates an album based off the Photo search results
	 * @param e
	 * @throws IOException
	 */
	public void createAlbum(ActionEvent e) throws IOException{
		ArrayList<Photo> result = new ArrayList<Photo>();
		if(obsList==null)return;
		for(Photo p: obsList) {
			result.add(p);
		}
		
		
		stageManager.getStage("Add_Album",currUser,result).showAndWait();
	}
	
	/**
	 * Updates the listview with the images that match the search parameters
	 */
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
		
		display.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Photo>() {
    	    @Override
    	    public void changed(ObservableValue<? extends Photo> obsList, Photo oldPhoto, Photo newPhoto) {
    	    	if(newPhoto!=null) {
    	    		String path = "file:///" + newPhoto.getPath();
    	    		Image image = new Image(path, true);
    	    		image_view.setImage(image);
    	    		
    	    		caption.setText(newPhoto.getCaption());
    	    		date.setText(newPhoto.getDateString());
    	    		tagsObsList = FXCollections.observableArrayList();
    	    		for (Tag tag : newPhoto.getTags()) {
    	    			tagsObsList.add(tag);
    	    		}
    	    		tagListView.setCellFactory(param -> new ListCell<Tag>() {
    	    			public void updateItem (Tag tag, boolean empty) {
    	    				super.updateItem(tag, empty);
    	    				if (empty) {
    	    					setText(null);
    	    				}
    	    				else {
    	    					setText(tag.toString());
    	    				}
    	    			}
    	    		});
    	    	}
    	    }
    	});		
		
	}
}

