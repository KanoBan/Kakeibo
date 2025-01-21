package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MvcApp extends Application {

	@Override
	public void start(Stage stage) {
	    try {
	        SceneSwitcher.setPrimaryStage(stage);

	        FXMLLoader loader;
	        String userDataFilePath = "user_data.json"; // ユーザーデータのファイルパス
	        if (JSONUtility.isInitialSetupDone(userDataFilePath)) {
	            loader = new FXMLLoader(getClass().getResource("/com/example/home.fxml"));
	        } else {
	            loader = new FXMLLoader(getClass().getResource("/com/example/initial.fxml"));
	        }

	        Scene scene = new Scene(loader.load());

	        // CSS ファイルのパスを修正
	        String cssPath = "/com/example/styles.css";
	        if (getClass().getResource(cssPath) != null) {
	            scene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
	        } else {
	            System.err.println("CSS ファイルが見つかりません: " + cssPath);
	        }

	        stage.setTitle("家計簿アプリ");
	        stage.setScene(scene);
	        stage.show();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}



    public static void main(String[] args) {
        launch();
    }
}
