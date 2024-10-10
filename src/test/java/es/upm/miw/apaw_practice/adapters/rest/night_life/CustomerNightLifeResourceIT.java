package es.upm.miw.apaw_practice.adapters.rest.night_life;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.night_life.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;

@RestTestConfig
class CustomerNightLifeResourceIT {
    @Autowired
    private WebTestClient webTestClient;
    @Test
    void TestDelete() {
        this.webTestClient
                .delete()
                .uri(CustomerNightLifeResource.CUSTOMERS + CustomerNightLifeResource.NAME_ID, "Sara")
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    void TestUpdate(){
        Customer updatedCustomer = new Customer("Conchi", "987654321", "conchi_updated@example.com");
        this.webTestClient
                .put()
                .uri(CustomerNightLifeResource.CUSTOMERS + CustomerNightLifeResource.NAME_ID, "Raul")
                .body(BodyInserters.fromValue(updatedCustomer))
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    void testGetOwnerPhonesByCustomerEmail() {
        String email = "example@upm.es";

        this.webTestClient
                .get()
                .uri(CustomerNightLifeResource.CUSTOMERS + CustomerNightLifeResource.EMAIL_ID, email)
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(phoneNumbers -> {
                    Assertions.assertEquals(1, phoneNumbers.size());
                    Assertions.assertTrue(phoneNumbers.contains("123456789"));
                });
    }

}
