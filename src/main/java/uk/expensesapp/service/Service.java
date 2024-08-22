package uk.expensesapp.service;

import static uk.expensesapp.Main.NAMESPACE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import uk.expensesapp.exception.BadRequestException;
import uk.expensesapp.mapper.IMapper;
import uk.expensesapp.model.mongo.ExpensesDocument;
import uk.expensesapp.model.request.ExpensesRequest;
import uk.expensesapp.model.response.ExpensesResponse;
import uk.expensesapp.repository.Repository;

@Component
public class Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(NAMESPACE);

    private final Repository repository;
    private final IMapper mapper;

    public Service(Repository repository, IMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ExpensesResponse getExpenses(final String id) {
        return mapper.map(repository.retrieveExpensesDocument(id));
    }

    public void upsertExpenses(ExpensesRequest request, final String id) {
        if (request.getSalary() < 1) {
            LOGGER.error("[salary] must equal 1 or greater");
            throw new BadRequestException("[salary] must equal 1 or greater");
        }
        ExpensesDocument document = mapper.mapRequest(request, id);
        repository.upsertExpensesDocument(document);
    }
}
