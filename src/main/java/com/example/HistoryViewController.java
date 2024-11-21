package com.example;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class HistoryViewController {
    private ExpenseManager expenseManager;
    private IncomeManager incomeManager;

    public HistoryViewController(ExpenseManager expenseManager, IncomeManager incomeManager) {
        this.expenseManager = expenseManager;
        this.incomeManager = incomeManager;
    }

    public ListView<String> createMonthlyHistoryView(int year, int month) {
        ObservableList<String> historyItems = FXCollections.observableArrayList();

        // 支出の履歴追加
        expenseManager.getExpensesByMonth(year, month).forEach(expense -> {
            historyItems.add("支出: " + expense.getCategory() + " - " + expense.getAmount() + "円 (" + expense.getDate() + ")");
        });

        // 収入の履歴追加
        incomeManager.getIncomesByMonth(year, month).forEach(income -> {
            historyItems.add("収入: " + income.getSource() + " - " + income.getAmount() + "円 (" + income.getDate() + ")");
        });

        ListView<String> historyListView = new ListView<>(historyItems);
        return historyListView;
    }
}
