package com.example;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HomeController {

    @FXML
    private Label balanceLabel;

    @FXML
    private TextField amountField;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private Button addButton;

    @FXML
    private Button incomeButton;

    @FXML
    private TextField incomeField;

    @FXML
    private TableView<Expense> dataTable;

    @FXML
    private TableColumn<Expense, String> dateColumn;

    @FXML
    private TableColumn<Expense, String> categoryColumn;

    @FXML
    private TableColumn<Expense, Double> amountColumn;

    @FXML
    private TableColumn<Expense, String> descriptionColumn;

    @FXML
    private PieChart pieChart;

    @FXML
    private LineChart<String, Number> lineChart;

    private double balance = 0;
    private final List<Expense> expenses = new ArrayList<>();

    @FXML
    public void initialize() {
        // 初期設定
        balance = 1000000; // 仮の残高
        categoryComboBox.getItems().addAll("食費", "交通費", "娯楽", "その他");
        updateBalanceLabel();

        // テーブル列設定
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        addButton.setOnAction(event -> addExpense());
        incomeButton.setOnAction(event -> addIncome());

        // グラフ初期化
        updatePieChart();
        updateLineChart();
    }

    private void addExpense() {
        String category = categoryComboBox.getValue();
        String amountText = amountField.getText();

        if (category == null || amountText.isEmpty()) {
            showAlert("エラー", "カテゴリーと金額を入力してください。");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            if (amount > balance) {
                showAlert("エラー", "残高を超える金額は入力できません。");
                return;
            }

            balance -= amount;
            expenses.add(new Expense("2025-01-21", category, amount, "支出の説明")); // 日付は仮
            dataTable.getItems().setAll(expenses);

            updateBalanceLabel();
            updatePieChart();
            updateLineChart();
            amountField.clear();

        } catch (NumberFormatException e) {
            showAlert("エラー", "金額は数値で入力してください。");
        }
    }

    private void addIncome() {
        try {
            double income = Double.parseDouble(incomeField.getText());
            balance += income;

            updateBalanceLabel();
            incomeField.clear();

        } catch (NumberFormatException e) {
            showAlert("エラー", "収入額は数値で入力してください。");
        }
    }

    private void updateBalanceLabel() {
        balanceLabel.setText(String.format("残高: ¥%,.2f", balance));
    }

    private void updatePieChart() {
        pieChart.getData().clear();
        for (Expense expense : expenses) {
            pieChart.getData().add(new PieChart.Data(expense.getCategory(), expense.getAmount()));
        }
    }

    private void updateLineChart() {
        lineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("日ごとの支出");

        for (int i = 0; i < expenses.size(); i++) {
            Expense expense = expenses.get(i);
            series.getData().add(new XYChart.Data<>("2025-01-" + (i + 1), expense.getAmount()));
        }

        lineChart.getData().add(series);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
