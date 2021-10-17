package es.upm.miw.apaw_practice.adapters.rest.emarketer;


import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.EmarketerSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.daos.CustomerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.entities.CustomerEntity;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.emarketer.Cups;
import es.upm.miw.apaw_practice.domain.models.emarketer.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
public class CustomerResourceIT {

    @Autowired
    CustomerRepository customerRepository;

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

    /*
    @Test
    void testUpdate() {
        assertTrue(this.customerRepository.findByName("Pedro").isPresent());
        CustomerEntity currentCustomer = this.customerRepository.findByName("Pedro").get();
        assertEquals("Madrid", currentCustomer.getAddress());
        assertEquals("particular", currentCustomer.getType());

        Customer customer = new Customer("Pedro", "Madrid", "empresa");

        this.webTestClient
                .patch()
                .uri(CustomerResource.CUSTOMERS + CustomerResource.NAME, customer.getName())
                .body(BodyInserters.fromValue(customer))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Cups.class);

        assertTrue(this.customerRepository.findByName("Pedro").isPresent());
        CustomerEntity updatedCustomer = this.customerRepository.findByName("Pedro").get();
        assertEquals("Madrid", updatedCustomer.getAddress());
        assertEquals("empresa", updatedCustomer.getType());

    }

     */
}
