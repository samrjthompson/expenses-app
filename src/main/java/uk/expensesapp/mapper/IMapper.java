package uk.expensesapp.mapper;

import uk.expensesapp.model.mongo.ExpensesDocument;
import uk.expensesapp.model.request.ExpensesRequest;
import uk.expensesapp.model.response.ExpensesResponse;

public interface IMapper {

    ExpensesResponse map(ExpensesDocument expensesDocument);

    ExpensesDocument mapRequest(ExpensesRequest request, final String id);
}
