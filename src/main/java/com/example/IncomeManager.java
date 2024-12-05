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

    public List<Income> getIncomes() {
        return incomes;
    }

    public List<Income> getIncomesByMonth(int year, int month) {
        return incomes.stream()
                      .filter(income -> income.getDate().getYear() == year &&
                                        income.getDate().getMonthValue() == month)
                      .collect(Collectors.toList());
    }
}
