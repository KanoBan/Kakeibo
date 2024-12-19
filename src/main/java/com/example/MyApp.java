package com.example;

import javafx.application.Application;
import javafx.stage.Stage;

public class MyApp extends Application {

    private Main mainAppInstance; // Mainアプリのインスタンス

    @Override
    public void start(Stage primaryStage) {
        mainAppInstance = new Main();
        
        // Mainクラスの機能を直接利用する
        mainAppInstance.start(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }
}
