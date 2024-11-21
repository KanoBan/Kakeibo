package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseManager {
    private List<Expense> expenses;

    public ExpenseManager() {
        this.expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void removeExpense(Expense expense) {
        expenses.remove(expense);
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    // 特定のカテゴリの支出を取得
    public List<Expense> getExpensesByCategory(String category) {
        return expenses.stream()
                       .filter(expense -> expense.getCategory().equals(category))
                       .collect(Collectors.toList());
    }

    // 特定の月の支出を取得
    public List<Expense> getExpensesByMonth(int year, int month) {
        return expenses.stream()
                       .filter(expense -> expense.getDate().getYear() == year &&
                                          expense.getDate().getMonthValue() == month)
                       .collect(Collectors.toList());
    }

    // カテゴリごとの合計を計算
    public double getTotalAmountByCategory(String category) {
        return expenses.stream()
                       .filter(expense -> expense.getCategory().equals(category))
                       .mapToDouble(Expense::getAmount)
                       .sum();

    
    }
}