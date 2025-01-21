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

            // FXML ファイルをロード
            FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlPath));
            if (loader.getLocation() == null) {
                throw new IllegalStateException("FXML file not found: " + fxmlPath);
            }

            // 新しいシーンを作成
            Parent root = loader.load();
            Scene newScene = new Scene(root);

            // CSS ファイルを適用
            String cssPath = "/com/example/styles.css";
            if (SceneSwitcher.class.getResource(cssPath) != null) {
                newScene.getStylesheets().add(SceneSwitcher.class.getResource(cssPath).toExternalForm());
            } else {
                System.err.println("CSS ファイルが見つかりません: " + cssPath);
            }

            // 新しいシーンをプライマリステージに設定
            primaryStage.setScene(newScene);

        } catch (IOException e) {
            System.err.println("Failed to switch to: " + fxmlPath);
            e.printStackTrace();
        } catch (IllegalStateException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
