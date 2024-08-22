package uk.expensesapp.mapper;

import org.springframework.stereotype.Component;
import uk.expensesapp.model.mongo.ExpensesDocument;
import uk.expensesapp.model.request.ExpensesRequest;
import uk.expensesapp.model.response.ExpensesResponse;

@Component
public class Mapper implements IMapper {

    @Override
    public ExpensesResponse map(ExpensesDocument expensesDocument) {
        return new ExpensesResponse()
                .salary(expensesDocument.getSalary())
                .totalExpenses(expensesDocument.getTotalExpenses())
                .totalIncome(expensesDocument.getTotalIncome());
    }

    @Override
    public ExpensesDocument mapRequest(ExpensesRequest request, final String id) {
        return new ExpensesDocument()
                .id(id)
                .salary(request.getSalary())
                .totalExpenses(request.getTotalExpenses())
                .totalIncome(request.getTotalIncome());
    }
}
