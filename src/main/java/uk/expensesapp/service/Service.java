package uk.expensesapp.service;

import static uk.expensesapp.Main.NAMESPACE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
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
    private final ICalculator incomeCalculator;
    private final ICalculator expensesCalculator;
    private final ICalculator spendingCalculator;
    private final ICalculator savingsCalculator;

    public Service(Repository repository, IMapper mapper,
                   @Qualifier("incomeCalculator") ICalculator incomeCalculator,
                   @Qualifier("expensesCalculator") ICalculator expensesCalculator,
                   @Qualifier("spendingCalculator") ICalculator spendingCalculator,
                   @Qualifier("savingsCalculator") ICalculator savingsCalculator) {
        this.repository = repository;
        this.mapper = mapper;
        this.incomeCalculator = incomeCalculator;
        this.expensesCalculator = expensesCalculator;
        this.spendingCalculator = spendingCalculator;
        this.savingsCalculator = savingsCalculator;
    }

    public ExpensesResponse getExpenses(final String id) {
        return mapper.mapResponse(repository.retrieveExpensesDocument(id));
    }

    public void upsertExpenses(ExpensesRequest request, final String id) {
        ExpensesDocument document = mapper.mapRequest(request, id);
        document = incomeCalculator.calculate(document);
        document = expensesCalculator.calculate(document);
        document = savingsCalculator.calculate(document);

        document = spendingCalculator.calculate(document);
        repository.upsertExpensesDocument(document);
    }
}
