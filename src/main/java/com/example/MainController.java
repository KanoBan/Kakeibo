package com.example;

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
    public void initialize() {
        // 各ボタンに画面遷移のイベントを設定
        manageButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/manage.fxml"));
        viewDataButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/data.fxml"));
        viewGraphButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/graph.fxml"));

        // 初期化ボタンに確認ダイアログを追加
        resetButton.setOnAction(event -> showResetConfirmationDialog());
    }

    private void showResetConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("初期化確認");
        alert.setHeaderText("本当に初期化しますか？");
        alert.setContentText("すべてのデータが削除されます。この操作は取り消せません。");

        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                resetApplication();
            }
        });
    }

    private void resetApplication() {
        JSONUtility.resetUserData("user_data.json"); // ユーザーデータをリセット
        SceneSwitcher.switchTo("/com/example/initial.fxml"); // 初期設定画面に戻る
    }
}
