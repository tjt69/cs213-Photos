package util;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.AddPhotoController;
import view.AddTagController;
import view.CopyPhotoController;
import view.EditCaptionController;
import view.MovePhotoController;
import view.PhotosController;

/**
 * Class Representation of a StageManager object
 * @author Justin Valeroso
 * @author Travis Thiel
 *
 */
// Helper class that holds methods to create Stages dynamically
public class StageManager {
	
	// Creates stage based off of input String sceneName
	/**
	 * Creates stage based off of input String sceneName for specific User user
	 * @param sceneName name of fxml and respective controller
	 * @param user current user signed in
	 * @return new Stage
	 * @throws IOException
	 */
	public Stage getStage (String sceneName, User user) throws IOException {
		// Set up controller
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/FXMLDocs/" + sceneName +".fxml"));
		Parent root = (Parent)loader.load();
		Controller controller = loader.getController();
				
		Stage secondaryStage = new Stage();
		controller.start(secondaryStage,user);
								
		// Set up secondaryStage
		Scene scene = new Scene(root);
		secondaryStage.setScene(scene);
		secondaryStage.setTitle("Photo App");
		secondaryStage.setResizable(false);
				
		return secondaryStage;
	}
	
	/**
	 * Creates stage based off of input String sceneName for specific User user
	 * @param sceneName name of fxml and respective controller
	 * @param user current user signed in
	 * @param result ArrayList of photos to be passed to the controller
	 * @return new Stage
	 * @throws IOException
	 */
	public Stage getStage (String sceneName, User user, ArrayList<Photo> result) throws IOException {
		// Set up controller
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/FXMLDocs/" + sceneName +".fxml"));
		Parent root = (Parent)loader.load();
		Controller controller = loader.getController();
				
		Stage secondaryStage = new Stage();
		controller.start(secondaryStage,user,result);
								
		// Set up secondaryStage
		Scene scene = new Scene(root);
		secondaryStage.setScene(scene);
		secondaryStage.setTitle("Photo App");
		secondaryStage.setResizable(false);
				
		return secondaryStage;
	}
	
	/**
	 * Creates stage based off of input String sceneName for specific User user
	 * @param sceneName name of fxml and respective controller
	 * @return new Stage
	 * @throws IOException
	 */
	public Stage getStage (String sceneName) throws IOException {
		// Set up controller
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/FXMLDocs/" + sceneName +".fxml"));
		Parent root = (Parent)loader.load();
		Controller controller = loader.getController();
				
		Stage secondaryStage = new Stage();
		controller.start(secondaryStage);
								
		// Set up secondaryStage
		Scene scene = new Scene(root);
		secondaryStage.setScene(scene);
		secondaryStage.setTitle("Photo App");
		secondaryStage.setResizable(false);
				
		return secondaryStage;
	}
	
	/**
	 * Creates stage based off of input String sceneName for specific User user when Adding a photo to album
	 * @param sceneName name of fxml and respective controller
	 * @param album Album being passed to Add_PhotoController
	 * @return new AddPhotoStage
	 * @throws IOException
	 */
	public Stage getAddPhotoStage (User currUser, Album album) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/FXMLDocs/Add_Photo.fxml"));
		Parent root = (Parent)loader.load();
		AddPhotoController addPhotoController = loader.getController();
		
		Stage secondaryStage = new Stage();
		addPhotoController.start(secondaryStage, currUser, album);
		
		Scene scene = new Scene(root);
		secondaryStage.setScene(scene);
		secondaryStage.setTitle("Photo App");
		secondaryStage.setResizable(false); 
		
		return secondaryStage;
	}
	
	// Loads a scene onto a stage
	public void loadScene (Stage primaryStage, String sceneName,User user) throws IOException {
		// Set up loginController
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/FXMLDocs/" + sceneName + ".fxml"));
		Parent root = (Parent)loader.load();
		Controller controller = loader.getController();
		controller.start(primaryStage,user);
				
		// Set up primaryStage
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Photo App");
		primaryStage.setResizable(false); 
		primaryStage.show();		
	}

	public void loadScene (Stage primaryStage, String sceneName) throws IOException {
		// Set up loginController
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/FXMLDocs/" + sceneName + ".fxml"));
		Parent root = (Parent)loader.load();
		Controller controller = loader.getController();
		controller.start(primaryStage);
				
		// Set up primaryStage
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Photo App");
		primaryStage.setResizable(false); 
		primaryStage.show();		
	}
	
	public void loadPhotosScene (Stage primaryStage, User currUser, Album selectedAlbum) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/FXMLDocs/Photos.fxml"));
		Parent root = (Parent)loader.load();
		PhotosController photosController = loader.getController();
		photosController.start(primaryStage, currUser, selectedAlbum);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Photo App");
		primaryStage.setResizable(false); 
		primaryStage.show();
	}
	
	public boolean getConfirmation () throws IOException {
		Confirmation c = new Confirmation();
		return c.confirmationAlert();
	}

	/**
	 * Creates stage based off of input String sceneName for specific User user when copying photo
	 * @param sceneName name of fxml and respective controller
	 * @param album original photo is located
	 * @param selectedPhoto Photo to be copied
	 * @return new CopyPhotoStage
	 * @throws IOException
	 */
	public Stage getCopyPhotoStage(User currUser, Album album, Photo selectedPhoto) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/FXMLDocs/Copy_Photo.fxml"));
		Parent root = (Parent)loader.load();
		CopyPhotoController copyPhotoController = loader.getController();
		
		Stage secondaryStage = new Stage();
		copyPhotoController.start(secondaryStage, currUser, album, selectedPhoto);
		
		Scene scene = new Scene(root);
		secondaryStage.setScene(scene);
		secondaryStage.setTitle("Photo App");
		secondaryStage.setResizable(false); 
		
		return secondaryStage;
	}
	
	/**
	 * Creates stage based off of input String sceneName for specific User user when copying photo
	 * @param sceneName name of fxml and respective controller
	 * @param album original photo is located
	 * @param selectedPhoto Photo to be moved
	 * @return new MovePhotoStage
	 * @throws IOException
	 */
	public Stage getMovePhotoStage(User currUser, Album album, Photo selectedPhoto) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/FXMLDocs/Move_Photo.fxml"));
		Parent root = (Parent)loader.load();
		MovePhotoController movePhotoController = loader.getController();
		
		Stage secondaryStage = new Stage();
		movePhotoController.start(secondaryStage, currUser, album, selectedPhoto);
		
		Scene scene = new Scene(root);
		secondaryStage.setScene(scene);
		secondaryStage.setTitle("Photo App");
		secondaryStage.setResizable(false); 
		
		return secondaryStage;
	}

	public Stage getAddTagStage(User currUser, Photo selectedPhoto) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/FXMLDocs/Add_Tag.fxml"));
		Parent root = (Parent)loader.load();
		AddTagController addTagController = loader.getController();
		
		Stage secondaryStage = new Stage();
		addTagController.start(secondaryStage, currUser, selectedPhoto);
		
		Scene scene = new Scene(root);
		secondaryStage.setScene(scene);
		secondaryStage.setTitle("Photo App");
		secondaryStage.setResizable(false); 
		
		return secondaryStage;
	}

	public Stage getEditCaptionStage(User currUser, Photo selectedPhoto) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/FXMLDocs/Edit_Caption.fxml"));
		Parent root = (Parent)loader.load();
		EditCaptionController editCaptionController = loader.getController();
		
		Stage secondaryStage = new Stage();
		editCaptionController.start(secondaryStage, currUser, selectedPhoto);
		
		Scene scene = new Scene(root);
		secondaryStage.setScene(scene);
		secondaryStage.setTitle("Photo App");
		secondaryStage.setResizable(false); 
		
		return secondaryStage;
	}	
	
}
