package es.upm.miw.apaw_practice.adapters.rest.tennis_courts;

import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.Tennis_CourtsSeederService;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class ReservationResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private Tennis_CourtsSeederService tennis_courtsSeederService;

    @Test
    void testDelete(){
        this.webTestClient.delete()
                .uri(ReservationResource.RESERVATIONS + "/Pedro" + "/30:9:21/12:00")
                .exchange()
                .expectStatus().isOk();

        this.webTestClient.delete()
                .uri(ReservationResource.RESERVATIONS + "/Pedro" + "/30:9:21/12:00")
                .exchange()
                .expectStatus().isOk();
        this.tennis_courtsSeederService.deleteAll();
        this.tennis_courtsSeederService.seedDatabase();
    }

    @Test
    void testUpdatePlayerList(){
        Player[] players = {
                new Player("00000006R"),
                new Player("00000007R")
        };
        this.webTestClient.patch()
                .uri(ReservationResource.RESERVATIONS + "/Pepe" + "/30:9:21" + "/13:00")
                .body(BodyInserters.fromValue(players))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Player.class)
                .value(playersDNIs -> assertEquals(4, playersDNIs.size()))
                .value(playersDNIs -> assertEquals("00000003R", playersDNIs.get(0).getDNI()));
        this.webTestClient.patch()
                .uri(ReservationResource.RESERVATIONS + "/Sandra" + "/30:9:21" + "/13:00")
                .body(BodyInserters.fromValue(players))
                .exchange()
                .expectStatus().isNotFound();

    }
}
