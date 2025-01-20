package com.example;

import java.util.List;

public class ReportGenerator {
    public String generateMonthlyReport(List<Expense> expenses, String month) {
        StringBuilder report = new StringBuilder();
        double totalExpenses = 0;

        report.append("Monthly Report for ").append(month).append(":\n");
        report.append("------------------------------------\n");

        for (Expense expense : expenses) {
            if (expense.getDate().startsWith(month)) {
                report.append(expense.toString()).append("\n");
                totalExpenses += expense.getAmount();
            }
        }

        report.append("------------------------------------\n");
        report.append("Total Expenses: $").append(String.format("%.2f", totalExpenses)).append("\n");
        return report.toString();
    }
}
