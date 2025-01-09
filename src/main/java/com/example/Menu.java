package com.example;

import java.util.Scanner;

public class Menu {

    public void displayMenu(User user, Scanner scanner) {
        while (true) {
            System.out.println("\n-- メニュー --");
            System.out.println("1. 支出の追加");
            System.out.println("2. 支出の削除");
            System.out.println("3. 月次レポートの表示");
            System.out.println("4. アプリを終了");
            System.out.print("選択してください: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    addExpense(user, scanner);
                    break;
                case 2:
                    removeExpense(user, scanner);
                    break;
                case 3:
                    generateReport(user);
                    break;
                case 4:
                    System.out.println("アプリを終了します。");
                    return;
                default:
                    System.out.println("無効な選択です。再度選択してください。");
            }
        }
    }

    private void addExpense(User user, Scanner scanner) {
        System.out.print("カテゴリーを入力してください: ");
        String category = scanner.nextLine();
        System.out.print("説明を入力してください: ");
        String description = scanner.nextLine();
        System.out.print("金額を入力してください: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Expense expense = new Expense(category, description, amount);
        user.addExpense(expense);

        System.out.println("支出が追加されました！");
        user.updateSavings();
    }

    private void removeExpense(User user, Scanner scanner) {
        System.out.println("-- 支出リスト --");
        for (int i = 0; i < user.getExpenses().size(); i++) {
            Expense expense = user.getExpenses().get(i);
            System.out.println((i + 1) + ". " + expense.getDescription() + " (" + expense.getAmount() + "円)");
        }
        System.out.print("削除したい支出の番号を入力してください: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        user.removeExpense(index - 1);
        System.out.println("支出が削除されました！");
        user.updateSavings();
    }

    private void generateReport(User user) {
        ReportGenerator report = new ReportGenerator();
        report.generate(user);
    }
}
