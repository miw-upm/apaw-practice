package es.upm.miw.apaw_practice.adapters.rest.museum;

import es.upm.miw.apaw_practice.adapters.mongodb.museum.MuseumSeederService;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.museum.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RestTestConfig
class RoomResourceIT {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private MuseumSeederService museumSeederService;

    @BeforeEach
    void resetDataBase() {
        this.museumSeederService.deleteAll();
        this.museumSeederService.seedDatabase();
    }

    @Test
    void testPatch() {
        Room room = new Room().builder()
                .description("Sala 012")
                .floor(1)
                .popularity(9.75)
                .build();
        Double updatedPopularity = 6.25;

        this.webTestClient
                .patch()
                .uri(RoomResource.ROOMS + RoomResource.DESCRIPTION_ID, room.getDescription())
                .body(BodyInserters.fromValue(updatedPopularity))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Room.class)
                .value(Assertions::assertNotNull)
                .value(response -> assertEquals(room.getDescription(), response.getDescription()))
                .value(response -> assertEquals(room.getFloor(), response.getFloor()))
                .value(response -> assertNotEquals(room.getPopularity(), response.getPopularity()))
                .value(response -> assertEquals(updatedPopularity, response.getPopularity()));
    }
}
