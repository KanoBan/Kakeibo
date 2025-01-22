package com.example;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HomeController {

    @FXML
    private Label balanceLabel;

    @FXML
    private TableView<Expense> summaryTable;

    @FXML
    private TableColumn<Expense, String> dateColumn;

    @FXML
    private TableColumn<Expense, String> categoryColumn;

    @FXML
    private TableColumn<Expense, Double> amountColumn;

    @FXML
    private TableColumn<Expense, String> descriptionColumn;

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

    private double balance = 1000000; // 初期残高
    private final List<Expense> expenses = new ArrayList<>();

    @FXML
    public void initialize() {
        // 残高を更新
        updateBalanceLabel();

        // テーブルのカラムにプロパティをバインド
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // サンプルデータを読み込み
        loadSampleData();

        // 各ボタンの画面遷移設定
        manageButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/manage.fxml"));
        viewDataButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/data.fxml"));
        viewGraphButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/graph.fxml"));
        categoryButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/category.fxml"));

        // 初期化ボタンの確認
        resetButton.setOnAction(event -> showResetConfirmationDialog());
    }

    private void updateBalanceLabel() {
        balanceLabel.setText(String.format("残高: ¥%,.2f", balance));
    }

    private void loadSampleData() {
        expenses.add(new Expense("2025-01-20", "食費", 2000, "スーパーで買い物"));
        expenses.add(new Expense("2025-01-19", "交通費", 1000, "電車代"));
        summaryTable.getItems().setAll(expenses);
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
        JSONUtility.resetUserData("user_data.json");
        SceneSwitcher.switchTo("/com/example/initial.fxml");
    }
}
