package com.example;

import java.util.ArrayList;
import java.util.List;

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

    private final List<Expense> expenses = new ArrayList<>();

    @FXML
    public void initialize() {
        loadPieChart();
        loadLineChart();

        // 戻るボタンの動作
        backButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/main.fxml"));
    }

    private void loadPieChart() {
        pieChart.getData().clear();
        for (Expense expense : expenses) {
            if (!"臨時収入".equals(expense.getCategory())) {
                pieChart.getData().add(new PieChart.Data(expense.getCategory(), expense.getAmount()));
            }
        }
    }

    private void loadLineChart() {
        lineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("日ごとの支出");

        for (int i = 0; i < expenses.size(); i++) {
            Expense expense = expenses.get(i);
            if (!"臨時収入".equals(expense.getCategory())) {
                series.getData().add(new XYChart.Data<>(expense.getDate(), expense.getAmount()));
            }
        }

        lineChart.getData().add(series);
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses.clear();
        this.expenses.addAll(expenses);
        loadPieChart();
        loadLineChart();
    }
}
