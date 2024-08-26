package uk.expensesapp.service;

import static uk.expensesapp.Main.NAMESPACE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import uk.expensesapp.model.mongo.ExpensesDocument;

@Component
@Qualifier("incomeCalculator")
public class IncomeCalculator implements ICalculator {

    private static final Logger LOGGER = LoggerFactory.getLogger(NAMESPACE);

    @Override
    public ExpensesDocument calculate(ExpensesDocument document) {
        LOGGER.info("Calculating total income");

        float totalIncome = 0;
        for (final float value : document.getIncome().values()) {
            totalIncome += value;
        }
        return document.totalIncome(totalIncome);
    }
}
