package com.example;
import java.util.Map;

public class DataVisualizer {
    public static void displayPieChart(Map<String, Double> data) {
        System.out.println("Pie Chart:");
        data.forEach((category, value) -> {
            System.out.println(category + ": " + value);
        });
    }

    public static void displayBarGraph(Map<String, Double> data) {
        System.out.println("Bar Graph:");
        data.forEach((category, value) -> {
            int barLength = (int) Math.round(value); //Doubleを四捨五入してintに変換
            String bar = "=".repeat(barLength); //repeatは正の整数が必要
            System.out.println(category + ": " + bar);
        });
    }

    public static void displaySummaryReport(Map<String, Double> data) {
        System.out.println("Summary Report:");
        double total = data.values().stream().mapToDouble(Double::doubleValue).sum();
        data.forEach((category, value) -> {
            double percentage = (value / total) * 100;
            System.out.printf("%s: %.2f%%\n", category, percentage);
        });
    }
}
