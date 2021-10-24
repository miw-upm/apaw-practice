package es.upm.miw.apaw_practice.adapters.rest.gym;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.gym.Athlete;
import es.upm.miw.apaw_practice.domain.models.gym.Gym;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void testAthletName() {
        String label = "Basic Fit";
        this.webTestClient
                .get()
                .uri(GymResource.Gyms + GymResource.SEARCH, label)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Athlete.class)
                .value(athletes -> assertEquals(2, athletes.size()))
                .value(athletes -> assertEquals(List.of("julia"),
                        athletes.stream().map(Athlete::getName).distinct()
                                .collect(Collectors.toList())));
    }


}
