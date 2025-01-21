package com.example;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class GraphController {

    @FXML
    private PieChart pieChart;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    public void initialize() {
        // 円グラフと折れ線グラフのデータをロード
        loadPieChart();
        loadLineChart();
    }

    private void loadPieChart() {
        // ダミーデータを円グラフに設定
        pieChart.getData().clear();
        pieChart.getData().add(new PieChart.Data("食費", 40));
        pieChart.getData().add(new PieChart.Data("交通費", 30));
        pieChart.getData().add(new PieChart.Data("娯楽", 20));
        pieChart.getData().add(new PieChart.Data("その他", 10));
    }

    private void loadLineChart() {
        // ダミーデータを折れ線グラフに設定
        lineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("日ごとの支出");

        series.getData().add(new XYChart.Data<>("2025-01-01", 100));
        series.getData().add(new XYChart.Data<>("2025-01-02", 200));
        series.getData().add(new XYChart.Data<>("2025-01-03", 150));
        series.getData().add(new XYChart.Data<>("2025-01-04", 250));

        lineChart.getData().add(series);
    }
}
