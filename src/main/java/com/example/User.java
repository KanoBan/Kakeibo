package com.example;

import java.util.List;

public class User {
    private double balance;        // 所持金
    private int payday;            // 給料日
    private List<String> categories; // カテゴリーリスト

    // デフォルトコンストラクター（必須）
    public User() {
    }

    // 引数付きコンストラクター
    public User(double balance, int payday, List<String> categories) {
        this.balance = balance;
        this.payday = payday;
        this.categories = categories;
    }

    // ゲッターとセッター
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getPayday() {
        return payday;
    }

    public void setPayday(int payday) {
        this.payday = payday;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
