package uk.expensesapp.mapper;

import org.springframework.stereotype.Component;
import uk.expensesapp.model.mongo.ExpensesDocument;
import uk.expensesapp.model.request.ExpensesRequest;
import uk.expensesapp.model.response.ExpensesResponse;

@Component
public class Mapper implements IMapper {

    @Override
    public ExpensesResponse mapResponse(ExpensesDocument expensesDocument) {
        return new ExpensesResponse();
    }

    @Override
    public ExpensesDocument mapRequest(ExpensesRequest request, final String id) {
        return new ExpensesDocument(id)
                .grossAnnualSalary(request.getGrossAnnualSalary())
                .netAnnualSalary(request.getNetAnnualSalary())
                .groceries(request.getGroceries())
                .fuel(request.getFuel())
                .bills(request.getBills())
                .income(request.getIncome())
                .savings(request.getSavings());
    }
}
