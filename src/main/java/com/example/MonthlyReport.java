package com.example;
public class MonthlyReport {
    private ExpenseManager expenseManager;
    private IncomeManager incomeManager;
    private int year;
    private int month;

    public MonthlyReport(ExpenseManager expenseManager, IncomeManager incomeManager, int year, int month) {
        this.expenseManager = expenseManager;
        this.incomeManager = incomeManager;
        this.year = year;
        this.month = month;
    }
 
    public void generateReport() {
        double totalIncome = incomeManager.getIncomesByMonth(year, month).stream()
                                          .mapToDouble(Income::getAmount)
                                          .sum();
        double totalExpenses = expenseManager.getExpensesByMonth(year, month).stream()
                                             .mapToDouble(Expense::getAmount)
                                             .sum();
        double balance = totalIncome - totalExpenses;
            
        System.out.println("===== " + month + "月の月次レポート =====");
        System.out.println("総収入: " + totalIncome + "円");
        System.out.println("総支出: " + totalExpenses + "円");
        System.out.println("残高: " + balance + "円");
        
        System.out.println("カテゴリ別支出:");
        expenseManager.getExpensesByMonth(year, month).stream()
                      .map(Expense::getCategory)
                      .distinct()
                      .forEach(category -> {
                          double categoryTotal = expenseManager.getTotalAmountByCategory(category);
                          System.out.println(category + ": " + categoryTotal + "円");
                      });
    }
}
