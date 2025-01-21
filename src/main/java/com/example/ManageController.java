package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ManageController {

    @FXML
    private Button backButton;

    @FXML
    public void initialize() {
        backButton.setOnAction(event -> {
            System.out.println("Returning to main screen.");
            SceneSwitcher.switchTo("/com/example/main.fxml");
        });
    }
}
