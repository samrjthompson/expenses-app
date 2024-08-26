package uk.expensesapp.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import uk.expensesapp.model.mongo.ExpensesDocument;

@Component
@Qualifier("spendingCalculator")
public class SpendingCalculator implements ICalculator {

    @Override
    public ExpensesDocument calculate(ExpensesDocument document) {
        return document.spendingMoney(
                document.getTotalIncome()
                        - document.getTotalExpenses()
                        - document.getTotalSavings());
    }
}
