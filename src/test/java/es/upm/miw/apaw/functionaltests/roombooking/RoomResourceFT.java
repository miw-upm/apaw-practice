package es.upm.miw.apaw.functionaltests.roombooking;

import es.upm.miw.apaw.adapters.in.roombooking.RoomResource;
import es.upm.miw.apaw.domain.model.roombooking.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.Map;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.RoomBookingSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class RoomResourceFT {

    @LocalServerPort
    private int port;

    private RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port)
                .build();
    }

    @Test
    void testRead() {
        this.restTestClient.get().uri(RoomResource.ROOMS + "/" + ROOM_ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Room.class)
                .value(body -> assertThat(body).usingRecursiveComparison().isEqualTo(ROOM_0));
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        this.restTestClient.get().uri(RoomResource.ROOMS + "/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message")).contains(id.toString()));
    }

    @Test
    void testFindAll() {
        this.restTestClient.get().uri(RoomResource.ROOMS)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Room[].class)
                .value(body -> assertThat(body).extracting(Room::getId)
                        .containsSubsequence(ROOM_ID_0, ROOM_ID_1));
    }

    @Test
    void testCreate() {
        this.restTestClient.post().uri(RoomResource.ROOMS)
                .body(Room.builder()
                        .name("FT Room " + UUID.randomUUID())
                        .capacity(30)
                        .floor(2)
                        .videoconferenceEquipped(true)
                        .build())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Room.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isNotNull();
                    assertThat(body.getCapacity()).isEqualTo(30);
                });
    }

    @Test
    void testCreateBlankName() {
        this.restTestClient.post().uri(RoomResource.ROOMS)
                .body(Room.builder().name(" ").capacity(10).floor(1).build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateDuplicateName() {
        this.restTestClient.post().uri(RoomResource.ROOMS)
                .body(Room.builder().name(ROOM_0.getName()).capacity(10).floor(1).build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdate() {
        Room room = this.createRoom();
        this.restTestClient.put().uri(RoomResource.ROOMS + "/" + room.getId())
                .body(Room.builder()
                        .name(room.getName())
                        .capacity(80)
                        .floor(4)
                        .videoconferenceEquipped(true)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Room.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isEqualTo(room.getId());
                    assertThat(body.getCapacity()).isEqualTo(80);
                    assertThat(body.getFloor()).isEqualTo(4);
                });
    }

    @Test
    void testUpdateNotFound() {
        this.restTestClient.put().uri(RoomResource.ROOMS + "/" + UUID.randomUUID())
                .body(Room.builder().name("Missing Room").capacity(10).floor(1).build())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateDuplicateName() {
        Room room = this.createRoom();
        this.restTestClient.put().uri(RoomResource.ROOMS + "/" + room.getId())
                .body(Room.builder().name(ROOM_0.getName()).capacity(10).floor(1).build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testPatch() {
        Room room = this.createRoom();
        this.restTestClient.patch().uri(RoomResource.ROOMS + "/" + room.getId())
                .body(Room.builder().capacity(200).build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Room.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getName()).isEqualTo(room.getName());
                    assertThat(body.getCapacity()).isEqualTo(200);
                });
    }

    @Test
    void testPatchNotFound() {
        this.restTestClient.patch().uri(RoomResource.ROOMS + "/" + UUID.randomUUID())
                .body(Room.builder().capacity(100).build())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchDuplicateName() {
        Room room = this.createRoom();
        this.restTestClient.patch().uri(RoomResource.ROOMS + "/" + room.getId())
                .body(Room.builder().name(ROOM_0.getName()).build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testDelete() {
        Room room = this.createRoom();
        this.restTestClient.delete().uri(RoomResource.ROOMS + "/" + room.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody().isEmpty();

        this.restTestClient.get().uri(RoomResource.ROOMS + "/" + room.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    private Room createRoom() {
        return this.restTestClient.post().uri(RoomResource.ROOMS)
                .body(Room.builder()
                        .name("FT Room " + UUID.randomUUID())
                        .capacity(25)
                        .floor(1)
                        .videoconferenceEquipped(false)
                        .build())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Room.class)
                .returnResult()
                .getResponseBody();
    }
}