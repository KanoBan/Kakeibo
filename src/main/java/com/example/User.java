package com.example;

import java.util.Scanner;

public class User {
    private String userName;
    private double monthlyIncome;
    private double savings;

    public void setupUserProfile(Scanner scanner) {
        System.out.print("Enter your name: ");
        userName = scanner.nextLine();
        System.out.print("Enter your monthly income: ");
        monthlyIncome = scanner.nextDouble();
        System.out.print("Enter your starting savings: ");
        savings = scanner.nextDouble();
        scanner.nextLine(); // consume newline
    }

    public String getUserName() {
        return userName;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public double getSavings() {
        return savings;
    }
}
