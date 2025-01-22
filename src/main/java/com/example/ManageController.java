package com.example;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ManageController {
    @FXML
    private Label balanceLabel;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private Button backButton;

    private double balance;

    @FXML
    public void initialize() {
        // ユーザーデータをロード
        User user = JSONUtility.loadUserData("user_data.json");
        if (user != null) {
            balance = user.getBalance();
            categoryComboBox.setItems(FXCollections.observableArrayList(user.getCategories()));
        } else {
            balance = 0.0; // デフォルト値
        }
        updateBalanceLabel();

        // 戻るボタンの設定
        backButton.setOnAction(event -> {
            System.out.println("戻るボタンがクリックされました");
            SceneSwitcher.switchTo("/com/example/main.fxml");
        });
    }

    private void updateBalanceLabel() {
        balanceLabel.setText(String.format("残高: ¥%,.2f", balance));
    }
}
