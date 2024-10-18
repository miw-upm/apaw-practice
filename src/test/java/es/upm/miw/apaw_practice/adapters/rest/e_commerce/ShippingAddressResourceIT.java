package es.upm.miw.apaw_practice.adapters.rest.e_commerce;
import es.upm.miw.apaw_practice.domain.models.e_commerce_model.ShippingAddress;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@RestTestConfig
public class ShippingAddressResourceIT {

    @Autowired
    private WebTestClient webTestClient;
    @Test
    void testUpdateRecipientName() {
        this.webTestClient
                .put()
                .uri(uriBuilder -> uriBuilder
                        .path("/e_commerce/shipping-addresses/{location}/recipient-name")
                        .build("C/Rey, 24"))
                .body(BodyInserters.fromValue("New Recipient"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(ShippingAddress.class)
                .value(shippingAddress -> {
                    assertNotNull(shippingAddress);
                    assertEquals("C/Rey, 24", shippingAddress.getLocation());
                    assertEquals("New Recipient", shippingAddress.getRecipientName());
                });
    }


    @Test
    void testUpdateRecipientNameNotFound() {
        this.webTestClient
                .put()
                .uri("/e_commerce/shipping-addresses/NonExistentAddress/recipient-name")
                .body(BodyInserters.fromValue("Recipient"))
                .exchange()
                .expectStatus().isNotFound();
    }
}
