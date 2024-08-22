package uk.expensesapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;
import uk.expensesapp.model.mongo.ExpensesDocument;
import uk.expensesapp.model.response.ExpensesResponse;

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest
@WireMockTest(httpPort = 8888)
class ControllerIT {

    private static final String GET_EXPENSES_ENDPOINT = "/expenses/{id}";
    private static final String PUT_EXPENSES_ENDPOINT = "/expenses/{id}";
    private static final String EXPENSES_ID = "expenses_id";
    private static final String COLLECTION_NAME = "expenses";

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.12");

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeEach
    void setUp() {
        mongoTemplate.dropCollection(COLLECTION_NAME);
        mongoTemplate.createCollection(COLLECTION_NAME);
    }

    @Test
    void shouldGetExpensesById() throws Exception {
        // given
        final String expectedResponseAsString = IOUtils.resourceToString(
                "/expected_response.json", StandardCharsets.UTF_8);
        ExpensesResponse expectedResponse = objectMapper.readValue(expectedResponseAsString, ExpensesResponse.class);

        String existingDocumentJson = IOUtils.resourceToString(
                "/example_doc.json", StandardCharsets.UTF_8);
        ExpensesDocument existingDocument = objectMapper.readValue(existingDocumentJson, ExpensesDocument.class);

        mongoTemplate.insert(existingDocument, COLLECTION_NAME);

        // when
        ResultActions result = mockMvc.perform(get(GET_EXPENSES_ENDPOINT, EXPENSES_ID)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        assertEquals(expectedResponse, objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), ExpensesResponse.class));
    }

    @Test
    void shouldInsertNewExpensesDocument() throws Exception {
        // given
        String requestBody = IOUtils.resourceToString(
                "/request_body.json", StandardCharsets.UTF_8);

        String expectedDocumentJson = IOUtils.resourceToString(
                "/example_doc.json", StandardCharsets.UTF_8);
        ExpensesDocument expectedDocument = objectMapper.readValue(expectedDocumentJson, ExpensesDocument.class);

        // when
        ResultActions result = mockMvc.perform(put(PUT_EXPENSES_ENDPOINT, EXPENSES_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        ExpensesDocument actualDocument = mongoTemplate.findById(EXPENSES_ID, ExpensesDocument.class);
        assertEquals(expectedDocument, actualDocument);
    }
}
