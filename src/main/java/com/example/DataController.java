package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DataController {

    @FXML
    private Button backButton;

    @FXML
    public void initialize() {
        backButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/main.fxml"));
    }
}
