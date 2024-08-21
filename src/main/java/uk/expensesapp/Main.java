package uk.expensesapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static final String NAMESPACE = "expenses-app";

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}