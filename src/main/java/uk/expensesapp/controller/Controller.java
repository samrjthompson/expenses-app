package uk.expensesapp.controller;

import static uk.expensesapp.Main.NAMESPACE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.expensesapp.model.response.ExpensesResponse;
import uk.expensesapp.service.Service;

@CrossOrigin // To be removed once spring security is set up
@RestController
public class Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(NAMESPACE);

    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping("/expenses/{id}")
    public ResponseEntity<ExpensesResponse> getExpenses(
            @PathVariable("id") final String id) {
        LOGGER.info("Processing GET expenses for id: {}", id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getExpenses(id));
    }
}
