package uk.expensesapp.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import uk.expensesapp.model.mongo.ExpensesDocument;

@Component
@Qualifier("savingsCalculator")
public class SavingsCalculator implements ICalculator {

    @Override
    public ExpensesDocument calculate(ExpensesDocument document) {
        float totalSavings = 0;
        for (final float value : document.getSavings().values()) {
            totalSavings += value;
        }
        return document.totalSavings(totalSavings);
    }
}
