package es.upm.miw.apaw_practice.adapters.rest.e_commerce;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.e_commerce_model.CustomerECommerce;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.apaw_practice.adapters.rest.e_commerce.CustomerECommerceResource.CUSTOMERS;
import static es.upm.miw.apaw_practice.adapters.rest.e_commerce.CustomerECommerceResource.SEARCH;
import static org.junit.jupiter.api.Assertions.assertEquals;
@RestTestConfig
public class CustomerECommerceResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindByUserName() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(CUSTOMERS + SEARCH)
                                .queryParam("userName", "user1")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(CustomerECommerce.class)
                .value(Assertions::assertNotNull)
                .value(customerECommerce -> {
                    assertEquals("user1", customerECommerce.getUserName());
                    assertEquals("user1@example.com", customerECommerce.getEmail());
                });
    }

    @Test
    void testFindByUsernameNotFound() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(CUSTOMERS + SEARCH)
                                .queryParam("userName", "non_existent_user")
                                .build())
                .exchange()
                .expectStatus().isNotFound();
    }
}
