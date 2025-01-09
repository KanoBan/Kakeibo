package com.example;

import java.util.Scanner;

public class BudgetApp {
    private User user;
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("家計簿アプリへようこそ！");
        initializeUser();

        Menu menu = new Menu();
        menu.displayMenu(user, scanner);
    }

    private void initializeUser() {
        System.out.print("ユーザー名を入力してください: ");
        String username = scanner.nextLine();
        System.out.print("月収を入力してください: ");
        double monthlyIncome = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline
        System.out.print("給料日を入力してください (例: 25): ");
        int payday = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        user = new User(username, monthlyIncome, payday);
        System.out.println("初期設定が完了しました！\n");
    }
}
