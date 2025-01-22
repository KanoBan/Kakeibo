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

        for (Expense expense : expenses) {
            if (!"臨時収入".equals(expense.getCategory())) {
                series.getData().add(new XYChart.Data<>(expense.getDate(), expense.getAmount()));
            }
        }

        lineChart.getData().add(series);
    }
}
