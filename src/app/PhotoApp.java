package app;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;

import util.StageManager;
import util.User;


public class PhotoApp extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		StageManager stageManager = new StageManager();
		stageManager.loadScene(primaryStage, "Login");
	}
	
	public static void main(String[] args) {
//		try {
//		FileOutputStream fileOut = new FileOutputStream("accounts.dat");
//		ObjectOutputStream out = new ObjectOutputStream(fileOut);
//		User user = new User("admin", "admin", "admin");
//		ArrayList<User>users = new ArrayList<User>();
//		users.add(user);
//		out.writeObject(users);
//		out.close();
//		fileOut.close();
//		System.out.println("test");
//		} catch(Exception e) {
//			
//		}
//		return;
		
		launch(args);
	}

}
