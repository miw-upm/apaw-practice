package es.upm.miw.apaw_practice.adapters.mongodb.emarketer.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class CustomerRepositoryIT {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.customerRepository.findAll().stream()
                .anyMatch(customer ->
                        "Pedro".equals(customer.getName())
                ));
    }

}
