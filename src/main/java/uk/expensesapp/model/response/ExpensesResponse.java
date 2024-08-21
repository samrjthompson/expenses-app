package uk.expensesapp.model.response;

import java.util.Objects;

public class ExpensesResponse {

    private float salary;
    private float totalIncome;
    private float totalExpenses;

    public float getSalary() {
        return salary;
    }

    public ExpensesResponse salary(float salary) {
        this.salary = salary;
        return this;
    }

    public float getTotalIncome() {
        return totalIncome;
    }

    public ExpensesResponse totalIncome(float totalIncome) {
        this.totalIncome = totalIncome;
        return this;
    }

    public float getTotalExpenses() {
        return totalExpenses;
    }

    public ExpensesResponse totalExpenses(float totalExpenses) {
        this.totalExpenses = totalExpenses;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpensesResponse that = (ExpensesResponse) o;
        return Float.compare(salary, that.salary) == 0 && Float.compare(totalIncome, that.totalIncome) == 0 && Float.compare(totalExpenses, that.totalExpenses) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(salary, totalIncome, totalExpenses);
    }
}
