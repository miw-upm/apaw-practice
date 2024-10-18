package es.upm.miw.apaw_practice.adapters.rest.company;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.company.daos.ExpenseBillRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.ExpenseBillEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestTestConfig
public class ExpenseBillResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ExpenseBillRepository expenseBillRepository;

    private String existingExpenseBillId;

    @BeforeEach
    void setUp() {
        ExpenseBillEntity expenseBill = new ExpenseBillEntity(
                "Test Description", new BigDecimal("100.0"), LocalDateTime.now(), List.of()
        );
        expenseBill = this.expenseBillRepository.save(expenseBill);
        existingExpenseBillId = expenseBill.getId();
    }

    @Test
    void testDeleteExpenseBill() {
        this.webTestClient
                .delete()
                .uri(ExpenseBillResource.EXPENSE_BILLS + "/" + existingExpenseBillId)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testDeleteExpenseBillNotFound() {
        String nonExistentId = "nonExistingId";

        this.webTestClient
                .delete()
                .uri(ExpenseBillResource.EXPENSE_BILLS + "/" + nonExistentId)
                .exchange()
                .expectStatus().isNotFound();
    }
}
