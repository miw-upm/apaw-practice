package es.upm.miw.apaw_practice.adapters.rest.cinema;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.cinema.Spectator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDate;
import java.util.List;

@RestTestConfig
public class ScreenResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdate() {
        List<Spectator> spectatorList = List.of(new Spectator[]{
                new Spectator("544578J", "Maria", "Vernia", LocalDate.of(2021, 04, 10)),
                new Spectator("544588J", "Marta", "Vernia", LocalDate.of(2021, 04, 10)),
                new Spectator("994578J", "Javier", "Vernia", LocalDate.of(2021, 04, 10))
        });
        this.webTestClient
                .put()
                .uri(ScreenResource.SCREENS + ScreenResource.NUMBER_ID, 2)
                .body(BodyInserters.fromValue(spectatorList))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testGetActorsNameByScreenNumber() {
        this.webTestClient
                .get()
                .uri(ScreenResource.SCREENS + ScreenResource.NUMBER_ID + ScreenResource.ACTORS_NAME, 2)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class)
                .value(name -> name.get(0).equals("Jennifer"));
    }

}
