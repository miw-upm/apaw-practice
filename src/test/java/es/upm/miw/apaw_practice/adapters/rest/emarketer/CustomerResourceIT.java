package es.upm.miw.apaw_practice.adapters.rest.emarketer;


import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.emarketer.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
public class CustomerResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadAll() {
        this.webTestClient
                .get()
                .uri(CustomerResource.CUSTOMERS)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Customer.class)
                .value(customers -> assertTrue(customers.size() > 0))
                .value(customers -> assertEquals("Pedro", customers.get(0).getName()))
                .value(customers -> assertEquals("particular", customers.get(3).getType()))
                .value(customers -> assertEquals("Barcelona", customers.get(1).getAddress()))       ;
    }
}
