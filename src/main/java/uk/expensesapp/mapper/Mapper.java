package uk.expensesapp.mapper;

import org.springframework.stereotype.Component;
import uk.expensesapp.model.mongo.ExpensesDocument;
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
}
