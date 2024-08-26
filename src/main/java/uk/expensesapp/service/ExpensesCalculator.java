package uk.expensesapp.service;

import static uk.expensesapp.Main.NAMESPACE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import uk.expensesapp.model.mongo.ExpensesDocument;

@Component
@Qualifier("expensesCalculator")
public class ExpensesCalculator implements ICalculator {

    private static final Logger LOGGER = LoggerFactory.getLogger(NAMESPACE);

    @Override
    public ExpensesDocument calculate(ExpensesDocument document) {
        LOGGER.info("Calculating total expenses");

        float totalExpenses = 0;
        for (final float value : document.getBills().values()) {
            totalExpenses += value;
        }

        totalExpenses += document.getGroceries();
        totalExpenses += document.getFuel();

        return document.totalExpenses(totalExpenses);
    }
}
