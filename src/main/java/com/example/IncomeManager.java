package com.example;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IncomeManager {
    private List<Income> incomes;

    public IncomeManager() {
        this.incomes = new ArrayList<>();
    }

    public void addIncome(Income income) {
        incomes.add(income);
    }

    public void removeIncome(Income income) {
        incomes.remove(income);
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    // 特定の月の収入を取得
    public List<Income> getIncomesByMonth(int year, int month) {
        return incomes.stream()
                       .filter(income -> income.getDate().getYear() == year &&
                                         income.getDate().getMonthValue() == month)
                       .collect(Collectors.toList());
    }

    // カテゴリごとの合計を計算
    public double getTotalAmountBySource(String source) {
        return incomes.stream()
                      .filter(income -> income.getSource().equals(source))
                      .mapToDouble(Income::getAmount)
                      .sum();
    }
}
