package es.upm.miw.apaw.functionaltests.courthearing;

import es.upm.miw.apaw.adapters.in.courthearing.CourtResource;
import es.upm.miw.apaw.domain.model.courthearing.Court;
import es.upm.miw.apaw.domain.model.courthearing.CourtType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.time.LocalTime;
import java.util.Map;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.CourtHearingSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CourtResourceFT {
    @LocalServerPort
    private int port;
    private RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port).build();
    }

    @Test
    void testCreate() {
        Court court = this.newCourt();
        this.restTestClient.post().uri(CourtResource.COURTS)
                .body(court)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Court.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isNotNull();
                    assertThat(body.getName()).isEqualTo(court.getName());
                    assertThat(body.getPhone()).isEqualTo(court.getPhone());
                    assertThat(body.getOpeningTime()).isEqualTo(LocalTime.of(9, 0));
                    assertThat(body.getType()).isEqualTo(CourtType.CIVIL);
                });
    }

    @Test
    void testCreateWithoutPhoneTwice() {
        this.createCourt(null);
        this.createCourt(null);
    }

    @Test
    void testCreateBlankName() {
        this.restTestClient.post().uri(CourtResource.COURTS)
                .body(Court.builder().name(" ").address("Address").city("City").build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateDuplicateName() {
        Court court = this.newCourt();
        court.setName(COURT_0.getName());
        this.restTestClient.post().uri(CourtResource.COURTS)
                .body(court)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testCreateDuplicatePhone() {
        Court court = this.newCourt();
        court.setPhone(COURT_0.getPhone());
        this.restTestClient.post().uri(CourtResource.COURTS)
                .body(court)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testRead() {
        this.restTestClient.get().uri(CourtResource.COURTS + "/" + COURT_ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Court.class)
                .value(body -> assertThat(body).usingRecursiveComparison().isEqualTo(COURT_0));
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        this.restTestClient.get().uri(CourtResource.COURTS + "/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message")).contains(id.toString()));
    }

    @Test
    void testFindAll() {
        Court extra = this.createCourt(this.uniquePhone());
        this.restTestClient.get().uri(CourtResource.COURTS)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Court[].class)
                .value(body -> {
                    assertThat(body).extracting(Court::getId)
                            .contains(COURT_ID_0, COURT_ID_1, COURT_ID_2, COURT_ID_3, COURT_ID_4, extra.getId());
                    assertThat(body).extracting(Court::getId)
                            .containsSubsequence(COURT_ID_1, COURT_ID_2, COURT_ID_3, COURT_ID_0);
                });
    }

    @Test
    void testUpdate() {
        Court court = this.createCourt(this.uniquePhone());
        String newName = "Updated court " + UUID.randomUUID();
        this.restTestClient.put().uri(CourtResource.COURTS + "/" + court.getId())
                .body(Court.builder().name(newName).address("New address").city("Sevilla")
                        .type(CourtType.FAMILY).build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Court.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isEqualTo(court.getId());
                    assertThat(body.getName()).isEqualTo(newName);
                    assertThat(body.getCity()).isEqualTo("Sevilla");
                    assertThat(body.getPhone()).isNull();
                    assertThat(body.getOpeningTime()).isNull();
                    assertThat(body.getType()).isEqualTo(CourtType.FAMILY);
                });
        this.restTestClient.get().uri(CourtResource.COURTS + "/" + court.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Court.class)
                .value(body -> assertThat(body).isNotNull().extracting(Court::getName).isEqualTo(newName));
    }

    @Test
    void testUpdateSameUniqueValues() {
        Court court = this.createCourt(this.uniquePhone());
        court.setCity("Bilbao");
        this.restTestClient.put().uri(CourtResource.COURTS + "/" + court.getId())
                .body(court)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Court.class)
                .value(body -> assertThat(body).isNotNull().extracting(Court::getCity).isEqualTo("Bilbao"));
    }

    @Test
    void testUpdateNotFound() {
        this.restTestClient.put().uri(CourtResource.COURTS + "/" + UUID.randomUUID())
                .body(Court.builder().name("Missing").address("Address").city("City").build())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateDuplicateName() {
        Court court = this.createCourt(this.uniquePhone());
        this.restTestClient.put().uri(CourtResource.COURTS + "/" + court.getId())
                .body(Court.builder().name(COURT_0.getName()).address("Address").city("City").build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdateDuplicatePhone() {
        Court court = this.createCourt(this.uniquePhone());
        this.restTestClient.put().uri(CourtResource.COURTS + "/" + court.getId())
                .body(Court.builder().name(court.getName()).address("Address").city("City")
                        .phone(COURT_0.getPhone()).build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdateBlankName() {
        Court court = this.createCourt(this.uniquePhone());
        this.restTestClient.put().uri(CourtResource.COURTS + "/" + court.getId())
                .body(Court.builder().name(" ").address("Address").city("City").build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testPatch() {
        Court court = this.createCourt(this.uniquePhone());
        this.restTestClient.patch().uri(CourtResource.COURTS + "/" + court.getId())
                .body(Map.of("city", "Barcelona"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Court.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getCity()).isEqualTo("Barcelona");
                    assertThat(body).usingRecursiveComparison().ignoringFields("city")
                            .isEqualTo(court);
                });
    }

    @Test
    void testPatchEmptyBodyChangesNothing() {
        Court court = this.createCourt(this.uniquePhone());
        this.restTestClient.patch().uri(CourtResource.COURTS + "/" + court.getId())
                .body(Map.of())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Court.class)
                .value(body -> assertThat(body).usingRecursiveComparison().isEqualTo(court));
    }

    @Test
    void testPatchNotFound() {
        this.restTestClient.patch().uri(CourtResource.COURTS + "/" + UUID.randomUUID())
                .body(Map.of("city", "Barcelona"))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchDuplicateName() {
        Court court = this.createCourt(this.uniquePhone());
        this.restTestClient.patch().uri(CourtResource.COURTS + "/" + court.getId())
                .body(Map.of("name", COURT_0.getName()))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testPatchDuplicatePhone() {
        Court court = this.createCourt(this.uniquePhone());
        this.restTestClient.patch().uri(CourtResource.COURTS + "/" + court.getId())
                .body(Map.of("phone", COURT_0.getPhone()))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testPatchBlankName() {
        Court court = this.createCourt(this.uniquePhone());
        this.restTestClient.patch().uri(CourtResource.COURTS + "/" + court.getId())
                .body(Map.of("name", " "))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testDelete() {
        Court court = this.createCourt(this.uniquePhone());
        this.restTestClient.delete().uri(CourtResource.COURTS + "/" + court.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody().isEmpty();
        this.restTestClient.get().uri(CourtResource.COURTS + "/" + court.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteMissingCourt() {
        this.restTestClient.delete().uri(CourtResource.COURTS + "/" + UUID.randomUUID())
                .exchange()
                .expectStatus().isNoContent();
    }

    private Court newCourt() {
        return Court.builder()
                .name("FT court " + UUID.randomUUID())
                .address("FT address")
                .city("Madrid")
                .phone(this.uniquePhone())
                .openingTime(LocalTime.of(9, 0))
                .closingTime(LocalTime.of(17, 0))
                .type(CourtType.CIVIL)
                .build();
    }

    private Court createCourt(String phone) {
        Court court = this.newCourt();
        court.setPhone(phone);
        return this.restTestClient.post().uri(CourtResource.COURTS)
                .body(court)
                .exchange().expectStatus().isCreated()
                .expectBody(Court.class).returnResult().getResponseBody();
    }

    private String uniquePhone() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}