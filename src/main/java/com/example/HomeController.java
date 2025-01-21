package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {

    @FXML
    private Button manageButton;
    @FXML
    private Button viewDataButton;
    @FXML
    private Button viewGraphButton;
    @FXML
    private Button resetButton;

    @FXML
    public void initialize() {
        manageButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/manage.fxml"));
        viewDataButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/data.fxml"));
        viewGraphButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/graph.fxml"));
        resetButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/initial.fxml"));
    }
}
