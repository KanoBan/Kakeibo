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
            if (JSONUtility.isInitialSetupDone()) {
                loader = new FXMLLoader(getClass().getResource("/com/example/home.fxml"));
            } else {
                loader = new FXMLLoader(getClass().getResource("/com/example/initial.fxml"));
            }

            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("/com/example/application.css").toExternalForm());
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
