package es.upm.miw.apaw_practice.adapters.rest.night_life;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.night_life.Customer;
import es.upm.miw.apaw_practice.domain.models.night_life.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
class CustomerNightLifeResourceIT {
    @Autowired
    private WebTestClient webTestClient;
    @Test
    void TestDelete(){
        this.webTestClient
                .delete()
                .uri(CustomerNightLifeResource.CUSTOMERS + CustomerNightLifeResource.NAME_ID, "Sara")
                .exchange()
                .expectStatus().isOk();
    }
}
