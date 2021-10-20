package es.upm.miw.apaw_practice.adapters.rest.tennis_courts;

import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.Tennis_CourtsSeederService;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

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
}
