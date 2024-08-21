package uk.expensesapp.model.mongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "expenses")
public class ExpensesDocument {

    @Id
    @JsonProperty("_id")
    private String id;
    private float salary;
    private float totalIncome;
    private float totalExpenses;

    public String getId() {
        return id;
    }

    public ExpensesDocument id(String id) {
        this.id = id;
        return this;
    }

    public float getSalary() {
        return salary;
    }

    public ExpensesDocument salary(float salary) {
        this.salary = salary;
        return this;
    }

    public float getTotalIncome() {
        return totalIncome;
    }

    public ExpensesDocument totalIncome(float totalIncome) {
        this.totalIncome = totalIncome;
        return this;
    }

    public float getTotalExpenses() {
        return totalExpenses;
    }

    public ExpensesDocument totalExpenses(float totalExpenses) {
        this.totalExpenses = totalExpenses;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpensesDocument that = (ExpensesDocument) o;
        return Float.compare(salary, that.salary) == 0 && Float.compare(totalIncome, that.totalIncome) == 0 && Float.compare(totalExpenses, that.totalExpenses) == 0 && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, salary, totalIncome, totalExpenses);
    }
}
