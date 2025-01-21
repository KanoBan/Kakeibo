package com.example;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneSwitcher {
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void switchTo(String fxmlPath) {
        try {
            System.out.println("Switching to: " + fxmlPath);
            Parent root = FXMLLoader.load(SceneSwitcher.class.getResource(fxmlPath));
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            System.err.println("Failed to switch to: " + fxmlPath);
            e.printStackTrace();
        }
    }
}
