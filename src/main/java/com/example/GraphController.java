package com.example;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

public class GraphController {

    @FXML
    private PieChart pieChart;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private Button backButton;

    @FXML
    public void initialize() {
        // トランザクションのロード
        var transactions = JSONUtility.loadTransactions("transactions.json");

        // グラフをロード
        loadPieChart(transactions);
        loadLineChart(transactions);

        backButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/main.fxml"));
    }

    private void loadPieChart(Iterable<Transaction> transactions) {
        pieChart.getData().clear();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) { // 支出のみ
                pieChart.getData().add(new PieChart.Data(transaction.getCategory(), -transaction.getAmount()));
            }
        }
    }

    private void loadLineChart(Iterable<Transaction> transactions) {
        lineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("日ごとの支出");

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) { // 支出のみ
                series.getData().add(new XYChart.Data<>(transaction.getDate(), -transaction.getAmount()));
            }
        }

        lineChart.getData().add(series);
    }
}
