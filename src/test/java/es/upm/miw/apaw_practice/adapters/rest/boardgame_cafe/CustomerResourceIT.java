package es.upm.miw.apaw_practice.adapters.rest.boardgame_cafe;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Customer;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Membership;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
public class CustomerResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Membership membership = new Membership(2, "Gold", 6, new BigDecimal("3.0"));
        Customer customer = new Customer("mariano@gmail.com", "Mariano", LocalDate.of(1995, 5, 15), true, membership);
        this.webTestClient
                .post()
                .uri(CustomerResource.CUSTOMER)
                .bodyValue(customer)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Customer.class)
                .value(Assertions::assertNotNull)
                .value(returnCustomer -> {
                    assertEquals("mariano@gmail.com", returnCustomer.getEmail());
                    assertEquals("Mariano", returnCustomer.getName());
                    assertEquals(LocalDate.of(1995, 5, 15), returnCustomer.getBirthDate());
                    assertTrue(returnCustomer.isMember());
                    assertEquals("Gold", returnCustomer.getMembership().getType());
                });
    }

}
