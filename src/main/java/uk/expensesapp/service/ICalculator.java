package uk.expensesapp.service;

import uk.expensesapp.model.mongo.ExpensesDocument;

public interface ICalculator {

    ExpensesDocument calculate(ExpensesDocument document);
}
