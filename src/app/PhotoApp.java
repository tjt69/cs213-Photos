package app;
import view.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class PhotoApp extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Set up loginController
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/FXMLDocs/Login.fxml"));
		Pane root = (Pane)loader.load();
		LoginController loginController = loader.getController();
		loginController.start(primaryStage);
		
		// Set up primaryStage
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Photo App");
		primaryStage.setResizable(false); 
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
