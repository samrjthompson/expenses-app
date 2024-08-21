package uk.expensesapp.service;

import org.springframework.stereotype.Component;
import uk.expensesapp.mapper.IMapper;
import uk.expensesapp.model.response.ExpensesResponse;
import uk.expensesapp.repository.Repository;

@Component
public class Service {

    private final Repository repository;
    private final IMapper mapper;

    public Service(Repository repository, IMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ExpensesResponse getExpenses(final String id) {
        return mapper.map(repository.retrieveExpensesDocument(id));
    }
}
