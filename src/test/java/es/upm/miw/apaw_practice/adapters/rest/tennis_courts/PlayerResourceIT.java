package es.upm.miw.apaw_practice.adapters.rest.tennis_courts;

import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.Tennis_CourtsSeederService;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.CourtNumberList;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Equipment;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
class PlayerResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private Tennis_CourtsSeederService tennis_courtsSeederService;

    @Test
    void testCreate(){
        Player player = new Player("0L", "Javier", "Fernandez", 43);
        this.webTestClient
                .post()
                .uri(PlayerResource.PLAYERS)
                .body(BodyInserters.fromValue(player))
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    @Test
    void testUpdate(){
        Equipment[] equipments = {
                new Equipment("Ball", 5, new BigDecimal("1.5")),
                new Equipment("Racquet", 1, new BigDecimal("5")),
                new Equipment("Shoes", 4, new BigDecimal("4"))
        };
        this.webTestClient
                .put()
                .uri(PlayerResource.PLAYERS + "/" + "00000001R" + PlayerResource.EQUIPMENTS)
                .body(BodyInserters.fromValue(List.of(equipments)))
                .exchange()
                .expectStatus().isOk();

        this.webTestClient
                .put()
                .uri(PlayerResource.PLAYERS + "/" + "otro" + PlayerResource.EQUIPMENTS)
                .body(BodyInserters.fromValue(List.of(equipments)))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
        this.tennis_courtsSeederService.deleteAll();
        this.tennis_courtsSeederService.seedDatabase();
    }

    @Test
    void testGet(){
        this.webTestClient.get()
                .uri(PlayerResource.PLAYERS + "/Nacho" + PlayerResource.COURTS + PlayerResource.OCCUPIED)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CourtNumberList.class)
                .value(courtNumberList -> assertEquals(2, courtNumberList.getNumbers().size()))
                .value(courtNumberList -> assertEquals(2, courtNumberList.getNumbers().get(0)));
        this.webTestClient.get()
                .uri(PlayerResource.PLAYERS + "/Pepe" + PlayerResource.COURTS + PlayerResource.OCCUPIED)
                .exchange()
                .expectStatus().isNotFound();
    }
}

