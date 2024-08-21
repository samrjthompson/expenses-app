package uk.expensesapp.mapper;

import uk.expensesapp.model.mongo.ExpensesDocument;
import uk.expensesapp.model.response.ExpensesResponse;

public interface IMapper {

    ExpensesResponse map(ExpensesDocument expensesDocument);
}
