package com.example;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private double monthlyIncome;
    private int payday;
    private double savings;
    private final List<Expense> expenses;

    public User(String name, double monthlyIncome, int payday) {
        this.name = name;
        this.monthlyIncome = monthlyIncome;
        this.payday = payday;
        this.savings = 0.0;
        this.expenses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public int getPayday() {
        return payday;
    }

    public double getSavings() {
        return savings;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void removeExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
        }
    }

    public void updateSavings() {
        double totalExpenses = expenses.stream()
                                       .mapToDouble(Expense::getAmount)
                                       .sum();
        savings = monthlyIncome - totalExpenses;
    }
}
