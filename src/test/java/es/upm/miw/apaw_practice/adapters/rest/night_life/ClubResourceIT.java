package es.upm.miw.apaw_practice.adapters.rest.night_life;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.night_life.Club;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
class ClubResourceIT {
    @Autowired
    private WebTestClient webTestClient;
    @Test
    void TestRead(){
        this.webTestClient
                .get()
                .uri(ClubResource.CLUBS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Club.class)
                .value(Assertions::assertNotNull)
                .value(clubData -> {
                    assertEquals("Cuenca Club", clubData.get(0).getName());
                    assertEquals(1, clubData.get(0).getReservations().size());
                    assertEquals("Kapital", clubData.get(1).getName());
                });
    }
}
