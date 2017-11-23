package view;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
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
 * Controls the "Photos" stage
 * @author Travis Thiel
 * @author Justin Valeroso
 */
public class PhotosController extends Controller{
	private User currUser;
	private Album album;
	private ObservableList<Photo> obsList;
	private ObservableList<Tag> tagsObsList;
	
	@FXML ListView<Photo> photosListView;
	@FXML TitledPane photosTitledPane;
	@FXML ImageView selectedImageView;
	@FXML TextField captionTextField;
	@FXML TextField dateTakenTextField;
	@FXML ListView<Tag> tagsListView;
	
	StageManager stageManager = new StageManager();
	
	/**
	 * Initializes controller's private fields and sets up controller
	 * for stage
	 * @param primaryStage is the Stage that this controller controls
	 * @param currUser is the current User that's accessing this stage
	 * @param album is the album that the User is viewing
	 */
	public void start (Stage primaryStage, User currUser, Album selectedAlbum) {
		this.primaryStage = primaryStage;
		this.currUser = currUser;
		this.album = selectedAlbum;
		
		photosTitledPane.setText("Photos in " + selectedAlbum.getName());
		displayPhotos();
	}
	
	/**
	 * Loads the "Add_Photo" stage so the User can add a photo
	 * @throws IOException
	 */
	public void addPhoto () throws IOException {
		stageManager.getAddPhotoStage(currUser, album).showAndWait();
		displayPhotos();
	}
	
	/**
	 * Prompts the User if they want to delete the selected photo, and if the User accepts, delete the photo
	 * @throws IOException
	 */
	public void deletePhoto () throws IOException {
		Photo selectedPhoto = photosListView.getSelectionModel().getSelectedItem();
		if (selectedPhoto == null) {
			return;
		}
		
		if (stageManager.getConfirmation()) {
			album.deletePhoto(selectedPhoto);
			try {
				// Deserialize storedUsers data
				FileInputStream fileIn = new FileInputStream("accounts.dat");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				ArrayList<User> storedUsers = (ArrayList<User>) in.readObject();
				in.close();
				fileIn.close();
						
				// Traverse storedUsers and remove selected album
				for (User u : storedUsers) {
					if (currUser.equals(u)) {
						storedUsers.set(storedUsers.indexOf(u), currUser);
					}
				}
						
				// Serialize updated storedUsers
				FileOutputStream fileOut = new FileOutputStream("accounts.dat");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(storedUsers);
				out.close();
				fileOut.close();
				
				displayPhotos();
			}
			catch (ClassNotFoundException ex) {
				System.out.println("Class not found.");
			}
			catch (IOException ex) {
				System.out.println("Error reading file.");
			}			
			
		}
		
	}
	
	/**
	 * Loads the "Copy_Photo" stage so the User can copy photos
	 * @throws IOException
	 */
	public void copyPhoto () throws IOException {
		Photo selectedPhoto = photosListView.getSelectionModel().getSelectedItem();
		stageManager.getCopyPhotoStage(currUser, album, selectedPhoto).showAndWait();
	}
	
	/**
	 * Loads the "Move_Photo" stage so the User can move photos
	 * @throws IOException
	 */
	public void movePhoto () throws IOException {
		Photo selectedPhoto = photosListView.getSelectionModel().getSelectedItem();
		stageManager.getMovePhotoStage(currUser, album, selectedPhoto).showAndWait();
		displayPhotos();
	}
	
	/**
	 * Returns the User to the "Albums" stage
	 * @throws IOException
	 */
	public void goBack () throws IOException {
		stageManager.loadScene(primaryStage, "Albums", currUser);
	}
	
	/**
	 * Loads the "Edit_Caption" stage so the User can edit a photo's caption
	 * @throws IOException
	 */
	public void editCaption () throws IOException {
		Photo selectedPhoto = photosListView.getSelectionModel().getSelectedItem();
		stageManager.getEditCaptionStage(currUser, selectedPhoto).showAndWait();
		displayPhotos();
	}
	
	/**
	 * Loads the "Add_Tag" stage so the User can add a Tag to a photo's caption
	 * @throws IOException
	 */
	public void addTag () throws IOException {
		Photo selectedPhoto = photosListView.getSelectionModel().getSelectedItem();
		stageManager.getAddTagStage(currUser, selectedPhoto).showAndWait();
		displayPhotos();
	}
	
	/**
	 * Prompts the User if they want to delete the selected Tag, and if the User accepts, delete the Tag
	 * @throws IOException
	 */
	public void deleteTag() throws IOException {
		if (stageManager.getConfirmation()) {
			Tag tag = tagsListView.getSelectionModel().getSelectedItem();
			if (tag != null) {
				Photo selectedPhoto = photosListView.getSelectionModel().getSelectedItem();
				selectedPhoto.deleteTag(tag);
				currUser.saveUser();
			}
		}
		displayPhotos();
	}
	
	/**
	 * Selects the next Photo in the ListView
	 * @throws IOException
	 */
	public void nextPhoto () throws IOException {
		int index = photosListView.getSelectionModel().getSelectedIndex();
		photosListView.getSelectionModel().select(index+1);
	}
	
	/**
	 * Selects the previous Photo in the ListView
	 * @throws IOException
	 */
	public void prevPhoto () throws IOException {
		int index = photosListView.getSelectionModel().getSelectedIndex();
		if (index-1 >=0) {
			photosListView.getSelectionModel().select(index-1);
		}
	}
	
	/**
	 * Helper method that populates the ListView with the current User's photos
	 */
	private void displayPhotos () {
		ArrayList<Photo> photos = album.getPhotos();
		obsList = FXCollections.observableArrayList();
		
		for (Photo photo : photos) {
			obsList.add(photo);
		}
		
		photosListView.setItems(obsList);
		photosListView.setCellFactory(param -> new ListCell<Photo>() {
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
		photosListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Photo>() {
    	    @Override
    	    public void changed(ObservableValue<? extends Photo> obsList, Photo oldPhoto, Photo newPhoto) {
    	    	if(newPhoto!=null) {
    	    		String path = "file:///" + newPhoto.getPath();
    	    		Image image = new Image(path, true);
    	    		selectedImageView.setImage(image);
    	    		
    	    		captionTextField.setText(newPhoto.getCaption());
    	    		dateTakenTextField.setText(newPhoto.getDateString());
    	    		tagsObsList = FXCollections.observableArrayList();
    	    		for (Tag tag : newPhoto.getTags()) {
    	    			tagsObsList.add(tag);
    	    		}
    	    		tagsListView.setItems(tagsObsList);
    	    		tagsListView.setCellFactory(param -> new ListCell<Tag>() {
    	    			@Override
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
