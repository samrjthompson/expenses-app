package uk.expensesapp.model.request;

import java.util.HashMap;
import java.util.Map;

public class ExpensesRequest {

    private float netAnnualSalary;
    private float grossAnnualSalary;

    private float groceries;
    private float fuel;

    private final Map<String, Float> bills;
    private final Map<String, Float> income;
    private final Map<String, Float> savings;

    public ExpensesRequest() {
        bills = new HashMap<>();
        income = new HashMap<>();
        savings = new HashMap<>();
    }

    public Map<String, Float> getBills() {
        return bills;
    }

    public Map<String, Float> getIncome() {
        return income;
    }

    public Map<String, Float> getSavings() {
        return savings;
    }

    public float getNetAnnualSalary() {
        return netAnnualSalary;
    }

    public ExpensesRequest netAnnualSalary(float netAnnualSalary) {
        this.netAnnualSalary = netAnnualSalary;
        return this;
    }

    public float getGrossAnnualSalary() {
        return grossAnnualSalary;
    }

    public ExpensesRequest grossAnnualSalary(float grossAnnualSalary) {
        this.grossAnnualSalary = grossAnnualSalary;
        return this;
    }

    public float getGroceries() {
        return groceries;
    }

    public ExpensesRequest groceries(float groceries) {
        this.groceries = groceries;
        return this;
    }

    public float getFuel() {
        return fuel;
    }

    public ExpensesRequest fuel(float fuel) {
        this.fuel = fuel;
        return this;
    }
}