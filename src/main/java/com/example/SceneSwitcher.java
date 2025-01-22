package com.example;

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

            // リソースパスの解決（ClassLoader の使用）
            String resourcePath = fxmlPath.startsWith("/") ? fxmlPath.substring(1) : fxmlPath;
            var fxmlLocation = SceneSwitcher.class.getClassLoader().getResource(resourcePath);

            if (fxmlLocation == null) {
                throw new IllegalStateException("FXML file not found: " + fxmlPath);
            }

            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();
            Scene newScene = new Scene(root);

            // CSS の再設定
            String cssPath = "com/example/styles.css";
            var cssUrl = SceneSwitcher.class.getClassLoader().getResource(cssPath);
            if (cssUrl != null) {
                newScene.getStylesheets().clear();
                newScene.getStylesheets().add(cssUrl.toExternalForm());
                System.out.println("CSS loaded: " + cssUrl.toExternalForm());
            } else {
                System.err.println("CSS file not found: " + cssPath);
            }

            primaryStage.setScene(newScene);
        } catch (Exception e) {
            System.err.println("Failed to switch to: " + fxmlPath);
            e.printStackTrace();
        }
    }
}
