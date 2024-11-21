package com.example;
public class BalanceCalculator {
    private ExpenseManager expenseManager;
    private IncomeManager incomeManager;

    public BalanceCalculator(ExpenseManager expenseManager, IncomeManager incomeManager) {
        this.expenseManager = expenseManager;
        this.incomeManager = incomeManager;
    }

    public double calculateBalance() {
        double totalIncome = incomeManager.getIncomes().stream()
                                          .mapToDouble(Income::getAmount)
                                          .sum();
        double totalExpenses = expenseManager.getExpenses().stream()
                                             .mapToDouble(Expense::getAmount)
                                             .sum();
        return totalIncome - totalExpenses;
    }
}
