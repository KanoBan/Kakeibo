package com.example;

public class ReportGenerator {

    public void generate(User user) {
        System.out.println("\n-- 月次レポート --");
        System.out.println("ユーザー名: " + user.getName());
        System.out.println("月収: " + user.getMonthlyIncome() + "円");
        System.out.println("給料日: " + user.getPayday() + "日");
        System.out.println("貯金額: " + user.getSavings() + "円");
        
        System.out.println("\n-- 支出の詳細 --");
        double totalExpenses = 0;
        for (Expense expense : user.getExpenses()) {
            System.out.println(expense.getCategory() + ": " + expense.getDescription() + " (" + expense.getAmount() + "円)");
            totalExpenses += expense.getAmount();
        }
        
        System.out.println("\n総支出額: " + totalExpenses + "円");
        System.out.println("差額: " + (user.getMonthlyIncome() - totalExpenses) + "円");
    }
}
