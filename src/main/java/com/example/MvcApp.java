package com.example;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MvcApp extends Application {

    @Override
    public void start(Stage stage) {
		try {
			// Create Loader for .fxml
			var mainViewLoader = new FXMLLoader(getClass().getResource("/com/example/main.fxml"));


			// Get View
			Parent root = mainViewLoader.load();

			// Get Controller
			MainController mainController = mainViewLoader.getController(); 
			
			
			var model = new Model();
			mainController.initModel(model);

			// Build scene and stage to show View on the screen
			var scene = new Scene(root);
<<<<<<< HEAD
			scene.getStylesheets().add(getClass().getResource("/com/example/styles.css").toExternalForm());
			stage.setTitle("家計簿アプリ");
			stage.setScene(scene);
			stage.show();

=======
			stage.setTitle("Memo");
			stage.setScene(scene);
			stage.show();
>>>>>>> 14bab891c1200f6ddf9532abdf7926dcb80185f0
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void main(String[] args) {
        launch();
    }

}