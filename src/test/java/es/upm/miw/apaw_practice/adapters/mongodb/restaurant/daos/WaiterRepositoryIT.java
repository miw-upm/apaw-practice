package es.upm.miw.apaw_practice.adapters.mongodb.restaurant.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class WaiterRepositoryIT {

    @Autowired
    private WaiterRepository waiterRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.waiterRepository.findAll().stream()
                .anyMatch(waiter ->
                        ("manager".equals(waiter.getCategory()) ||
                                "employee".equals(waiter.getCategory())) &&
                                ("dining room".equals(waiter.getSection()) ||
                                        "terrace".equals(waiter.getSection()))));
    }
}
