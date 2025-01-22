package com.example;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class MainController {

    @FXML
    private Button manageButton;

    @FXML
    private Button viewDataButton;

    @FXML
    private Button viewGraphButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button categoryButton;

    @FXML
    public void initialize() {
        manageButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/manage.fxml"));
        viewDataButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/data.fxml"));
        viewGraphButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/graph.fxml"));
        categoryButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/category.fxml"));

        resetButton.setOnAction(event -> showResetConfirmationDialog());
    }

    private void showResetConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("初期化確認");
        alert.setHeaderText("本当に初期化しますか？");
        alert.setContentText("全てのデータが削除されます。この操作は取り消せません。");

        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                resetApplication();
            }
        });
    }

    private void resetApplication() {
        JSONUtility.saveUserData(new User(0.0, 1, new ArrayList<>()), "user_data.json");
        JSONUtility.saveTransactions(new ArrayList<>(), "transactions.json");
        SceneSwitcher.switchTo("/com/example/initial.fxml");
    }
}
