package com.example;

import java.util.ArrayList;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HomeController {

    @FXML
    private Label balanceLabel;

    @FXML
    private TableView<Transaction> summaryTable;

    @FXML
    private TableColumn<Transaction, String> dateColumn;

    @FXML
    private TableColumn<Transaction, String> categoryColumn;

    @FXML
    private TableColumn<Transaction, Double> amountColumn;

    @FXML
    private TableColumn<Transaction, String> descriptionColumn;

    @FXML
    private Button manageButton;

    @FXML
    private Button viewDataButton;

    @FXML
    private Button viewGraphButton;

    @FXML
    private Button categoryButton;

    @FXML
    private Button resetButton;

    private double balance;

    @FXML
    public void initialize() {
        // ユーザーデータをロード
        User user = JSONUtility.loadUserData("user_data.json");
        if (user != null) {
            balance = user.getBalance();
        } else {
            balance = 0.0;
        }
        updateBalanceLabel();

        // トランザクションデータをロード
        ObservableList<Transaction> transactions = FXCollections.observableArrayList(JSONUtility.loadTransactions("transactions.json"));

        // テーブル設定
        dateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDate()));
        categoryColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategory()));
        amountColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getAmount()).asObject());
        descriptionColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));

        summaryTable.setItems(transactions);

        // ボタン設定
        manageButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/manage.fxml"));
        viewDataButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/data.fxml"));
        viewGraphButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/graph.fxml"));
        categoryButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/category.fxml"));
        resetButton.setOnAction(event -> showResetConfirmationDialog());
    }

    private void updateBalanceLabel() {
        balanceLabel.setText(String.format("残高: ¥%,.2f", balance));
    }

    private void showResetConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("初期化確認");
        alert.setHeaderText("本当に初期化しますか？");
        alert.setContentText("すべてのデータが削除されます。この操作は取り消せません。");

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
