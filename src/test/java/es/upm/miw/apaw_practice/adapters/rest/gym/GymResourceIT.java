package es.upm.miw.apaw_practice.adapters.rest.gym;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.gym.Athlete;
import es.upm.miw.apaw_practice.domain.models.gym.Coach;
import es.upm.miw.apaw_practice.domain.models.gym.Gym;
import es.upm.miw.apaw_practice.domain.models.gym.Lesson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class GymResourceIT {
    @Autowired
    private WebTestClient webTestClient;


    @Test
    void testFindByLabel() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(GymResource.Gyms + GymResource.Search)
                                .queryParam("label", "Basic Fit")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Gym.class)
                .value(gyms -> assertEquals("calle Gran via 82", gyms.get(0).getAddress()));

    }


    @Test
    void testUpdateCellphone() {
        Athlete athlete1 = new Athlete("55555555a", "ana", "ramos");

        Lesson lesson = new Lesson("BodyComba", LocalDateTime.of(2020, 9, 5, 5, 15)
                , "lesMils", false, List.of(athlete1, athlete1));
        Coach coach = new Coach("2356892A", "Terry", "Ryan", 11112, lesson);

        Gym gym = new Gym("calle toledo 32", "Basic Fi", "66666666", List.of(coach));

        this.webTestClient
                .put()
                .uri(GymResource.Gyms + GymResource.Address + GymResource.cellphone, "calle toledo 32")
                .body(BodyInserters.fromValue(gym))
                .exchange()
                .expectStatus().isOk();


        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(GymResource.Gyms + GymResource.Address1)
                                .queryParam("address", "calle toledo 32")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Gym.class)
                .value(gyms -> assertEquals("66666666", gyms.get(0).getCellphone()));
    }
}
