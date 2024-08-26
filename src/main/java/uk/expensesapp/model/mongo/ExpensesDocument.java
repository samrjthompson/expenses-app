package uk.expensesapp.model.mongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "expenses")
public class ExpensesDocument {

    @Id
    @JsonProperty("_id")
    private final String id;

    // Totals
    private float totalIncome;
    private float totalSavings;
    private float totalExpenses;
    private float netAnnualSalary;
    private float grossAnnualSalary;

    private float groceries;
    private float fuel;
    private float spendingMoney;

    private Map<String, Float> bills;
    private Map<String, Float> income;
    private Map<String, Float> savings;

    public ExpensesDocument(String id) {
        this.id = id;
    }

    public float getSpendingMoney() {
        return spendingMoney;
    }

    public float getTotalSavings() {
        return totalSavings;
    }

    public ExpensesDocument totalSavings(float totalSavings) {
        this.totalSavings = totalSavings;
        return this;
    }

    public ExpensesDocument spendingMoney(float spendingMoney) {
        this.spendingMoney = spendingMoney;
        return this;
    }

    public float getFuel() {
        return fuel;
    }

    public ExpensesDocument fuel(float fuel) {
        this.fuel = fuel;
        return this;
    }

    public float getGroceries() {
        return groceries;
    }

    public ExpensesDocument groceries(float groceries) {
        this.groceries = groceries;
        return this;
    }

    public float getGrossAnnualSalary() {
        return grossAnnualSalary;
    }

    public ExpensesDocument grossAnnualSalary(float grossAnnualSalary) {
        this.grossAnnualSalary = grossAnnualSalary;
        return this;
    }

    public float getNetAnnualSalary() {
        return netAnnualSalary;
    }

    public ExpensesDocument netAnnualSalary(float netAnnualSalary) {
        this.netAnnualSalary = netAnnualSalary;
        return this;
    }

    public float getTotalExpenses() {
        return totalExpenses;
    }

    public ExpensesDocument totalExpenses(float totalExpenses) {
        this.totalExpenses = totalExpenses;
        return this;
    }

    public float getTotalIncome() {
        return totalIncome;
    }

    public ExpensesDocument totalIncome(float totalIncome) {
        this.totalIncome = totalIncome;
        return this;
    }

    public String getId() {
        return id;
    }

    public Map<String, Float> getSavings() {
        return savings;
    }

    public ExpensesDocument savings(Map<String, Float> savings) {
        this.savings = savings;
        return this;
    }

    public Map<String, Float> getIncome() {
        return income;
    }

    public ExpensesDocument income(Map<String, Float> income) {
        this.income = income;
        return this;
    }

    public Map<String, Float> getBills() {
        return bills;
    }

    public ExpensesDocument bills(Map<String, Float> bills) {
        this.bills = bills;
        return this;
    }
}
