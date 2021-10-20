package es.upm.miw.apaw_practice.adapters.rest.gym;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.gym.Gym;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class GymResourceIT {
    @Autowired
    private WebTestClient webTestClient;



    @Test
    void testUpdateCellphone() {


        Gym gym = new Gym("calle toledo 32", "Basic Fi", "66666666", null);

        this.webTestClient
                .put()
                .uri(GymResource.Gyms + GymResource.Address + GymResource.cellphone, "calle toledo 32")
                .body(BodyInserters.fromValue(gym))
                .exchange()
                .expectStatus().isOk();
    }

}
