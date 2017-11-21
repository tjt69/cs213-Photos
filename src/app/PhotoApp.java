package app;
import javafx.application.Application;
import javafx.stage.Stage;

import util.StageManager;


public class PhotoApp extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		StageManager stageManager = new StageManager();
		stageManager.loadScene(primaryStage, "Login");
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
