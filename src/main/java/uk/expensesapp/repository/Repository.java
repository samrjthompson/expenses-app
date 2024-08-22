package uk.expensesapp.repository;

import static uk.expensesapp.Main.NAMESPACE;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import uk.expensesapp.exception.NotFoundException;
import uk.expensesapp.exception.ServiceUnavailableException;
import uk.expensesapp.model.mongo.ExpensesDocument;

@Component
public class Repository {

    private static final Logger LOGGER = LoggerFactory.getLogger(NAMESPACE);

    private final MongoTemplate mongoTemplate;

    public Repository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public ExpensesDocument retrieveExpensesDocument(final String id) {
        try {
            return Optional.ofNullable(mongoTemplate.findById(id, ExpensesDocument.class))
                    .orElseThrow(() -> {
                        LOGGER.error("No expenses found with id: {}", id);
                        return new NotFoundException("No expenses found with id: %s".formatted(id));
                    });
        } catch (DataAccessException ex) {
            LOGGER.error("MongoDB connection failed");
            throw new ServiceUnavailableException("MongoDB connection failed", ex);
        }
    }

    public void upsertExpensesDocument(ExpensesDocument document) {
        try {
            mongoTemplate.save(document);
        } catch (DataAccessException ex) {
            LOGGER.error("MongoDB connection failed");
            throw new ServiceUnavailableException("MongoDB connection failed", ex);
        }
    }
}
