package com.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManageController {
    @FXML
    private Label balanceLabel;
    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private TextField amountField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField incomeAmountField;
    @FXML
    private TableView<Transaction> transactionTable;
    @FXML
    private TableColumn<Transaction, String> dateColumn;
    @FXML
    private TableColumn<Transaction, String> categoryColumn;
    @FXML
    private TableColumn<Transaction, Double> amountColumn;
    @FXML
    private TableColumn<Transaction, String> descriptionColumn;
    @FXML
    private Button addExpenseButton;
    @FXML
    private Button addIncomeButton;
    @FXML
    private Button backButton;

    private ObservableList<Transaction> transactions = FXCollections.observableArrayList();
    private double balance;

    @FXML
    public void initialize() {
        User user = JSONUtility.loadUserData("user_data.json");
        if (user != null) {
            balance = user.getBalance();
            categoryComboBox.setItems(FXCollections.observableArrayList(user.getCategories()));
        } else {
            balance = 0.0;
        }
        transactions.addAll(JSONUtility.loadTransactions("transactions.json"));
        updateBalanceLabel();

        // テーブル設定
        dateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDate()));
        categoryColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategory()));
        amountColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getAmount()).asObject());
        descriptionColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));

        transactionTable.setItems(transactions);

        addExpenseButton.setOnAction(event -> addTransaction(-1));
        addIncomeButton.setOnAction(event -> addIncome());
        backButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/main.fxml"));
    }

    private void addTransaction(int multiplier) {
        String category = categoryComboBox.getValue();
        String description = descriptionField.getText();
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (category == null || category.isEmpty() || amountField.getText().isEmpty()) {
            showAlert("エラー", "すべての入力フィールドを正しく入力してください。");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountField.getText()) * multiplier;
        } catch (NumberFormatException e) {
            showAlert("エラー", "金額を正しく入力してください。");
            return;
        }

        Transaction transaction = new Transaction(date, category, amount, description);
        transactions.add(transaction);
        JSONUtility.saveTransactions(transactions, "transactions.json");

        balance += amount;
        updateBalanceLabel();

        // 入力フィールドをクリア
        amountField.clear();
        descriptionField.clear();
    }

    private void addIncome() {
        String description = descriptionField.getText();
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (incomeAmountField.getText().isEmpty()) {
            showAlert("エラー", "臨時収入金額を入力してください。");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(incomeAmountField.getText());
        } catch (NumberFormatException e) {
            showAlert("エラー", "金額を正しく入力してください。");
            return;
        }

        Transaction transaction = new Transaction(date, "臨時収入", amount, description);
        transactions.add(transaction);
        JSONUtility.saveTransactions(transactions, "transactions.json");

        balance += amount;
        updateBalanceLabel();

        // 入力フィールドをクリア
        incomeAmountField.clear();
        descriptionField.clear();
    }

    private void updateBalanceLabel() {
        balanceLabel.setText(String.format("残高: ¥%,.2f", balance));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
