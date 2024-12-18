package com.example;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class ChartController {
 
    public PieChart createCategoryPieChart(ExpenseManager manager) {
        PieChart pieChart = new PieChart();
        manager.getExpenses().stream()
               .map(Expense::getCategory)
               .distinct()
               .forEach(category -> {
                   double total = manager.getTotalAmountByCategory(category);
                   PieChart.Data slice = new PieChart.Data(category, total);
                   pieChart.getData().add(slice);
               });
        return pieChart;
    }

    public BarChart<String, Number> createMonthlyBarChart(ExpenseManager manager, int year) {
        BarChart<String, Number> barChart = new BarChart<>(new CategoryAxis(), new NumberAxis());
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for (int month = 1; month <= 12; month++) {
            double total = manager.getExpensesByMonth(year, month).stream()
                                   .mapToDouble(Expense::getAmount)
                                   .sum();
            series.getData().add(new XYChart.Data<>(month + "月", total));
        }

        barChart.getData().add(series);
        return barChart;
    }
}
