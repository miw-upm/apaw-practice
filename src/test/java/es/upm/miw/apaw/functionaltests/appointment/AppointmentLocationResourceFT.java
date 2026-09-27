package es.upm.miw.apaw.functionaltests.appointment;

import es.upm.miw.apaw.adapters.in.appointment.AppointmentLocationResource;
import es.upm.miw.apaw.domain.model.appointment.AppointmentLocation;
import es.upm.miw.apaw.domain.model.appointment.AppointmentLocationPatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.Map;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.AppointmentLocationSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AppointmentLocationResourceFT {

    @LocalServerPort
    private int port;
    private RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port).build();
    }

    @Test
    void testRead() {
        this.restTestClient.get()
                .uri(AppointmentLocationResource.APPOINTMENT_LOCATIONS + "/" + ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AppointmentLocation.class)
                .value(body -> assertThat(body).usingRecursiveComparison().isEqualTo(LOCATION_0));
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        this.restTestClient.get()
                .uri(AppointmentLocationResource.APPOINTMENT_LOCATIONS + "/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message")).contains(id.toString()));
    }

    @Test
    void testFindAll() {
        this.restTestClient.get()
                .uri(AppointmentLocationResource.APPOINTMENT_LOCATIONS)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AppointmentLocation[].class)
                .value(body -> assertThat(body).extracting(AppointmentLocation::getId)
                        .containsSubsequence(ID_2, ID_1, ID_0));
    }

    @Test
    void testCreate() {
        this.restTestClient.post()
                .uri(AppointmentLocationResource.APPOINTMENT_LOCATIONS)
                .body(AppointmentLocation.builder().name("FT location " + UUID.randomUUID())
                        .city("Madrid").build())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(AppointmentLocation.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isNotNull();
                    assertThat(body.getCreationDate()).isNotNull();
                });
    }

    @Test
    void testCreateBlankName() {
        this.restTestClient.post()
                .uri(AppointmentLocationResource.APPOINTMENT_LOCATIONS)
                .body(AppointmentLocation.builder().name(" ").city("Madrid").build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateDuplicateName() {
        this.restTestClient.post()
                .uri(AppointmentLocationResource.APPOINTMENT_LOCATIONS)
                .body(AppointmentLocation.builder().name(LOCATION_0.getName()).city("Madrid").build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdate() {
        AppointmentLocation location = this.createLocation();
        this.restTestClient.put()
                .uri(AppointmentLocationResource.APPOINTMENT_LOCATIONS + "/" + location.getId())
                .body(AppointmentLocation.builder().name(location.getName()).city("Sevilla").floor(5).build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(AppointmentLocation.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isEqualTo(location.getId());
                    assertThat(body.getCity()).isEqualTo("Sevilla");
                    assertThat(body.getFloor()).isEqualTo(5);
                });
    }

    @Test
    void testUpdateNotFound() {
        this.restTestClient.put()
                .uri(AppointmentLocationResource.APPOINTMENT_LOCATIONS + "/" + UUID.randomUUID())
                .body(AppointmentLocation.builder().name("Missing").city("Madrid").build())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateDuplicateName() {
        AppointmentLocation location = this.createLocation();
        this.restTestClient.put()
                .uri(AppointmentLocationResource.APPOINTMENT_LOCATIONS + "/" + location.getId())
                .body(AppointmentLocation.builder().name(LOCATION_0.getName()).city("Madrid").build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testDelete() {
        AppointmentLocation location = this.createLocation();
        this.restTestClient.delete()
                .uri(AppointmentLocationResource.APPOINTMENT_LOCATIONS + "/" + location.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody().isEmpty();
        this.restTestClient.get()
                .uri(AppointmentLocationResource.APPOINTMENT_LOCATIONS + "/" + location.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatch() {
        AppointmentLocation location = this.createLocation();
        String originalName = location.getName();
        this.restTestClient.patch()
                .uri(AppointmentLocationResource.APPOINTMENT_LOCATIONS + "/" + location.getId())
                .body(new AppointmentLocationPatch(null, "Calle Nueva 5", "Bilbao", null, null, 3))
                .exchange()
                .expectStatus().isOk()
                .expectBody(AppointmentLocation.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getName()).isEqualTo(originalName);
                    assertThat(body.getCity()).isEqualTo("Bilbao");
                    assertThat(body.getAddress()).isEqualTo("Calle Nueva 5");
                    assertThat(body.getFloor()).isEqualTo(3);
                });
    }

    private AppointmentLocation createLocation() {
        return this.restTestClient.post()
                .uri(AppointmentLocationResource.APPOINTMENT_LOCATIONS)
                .body(AppointmentLocation.builder().name("FT location " + UUID.randomUUID())
                        .city("Madrid").build())
                .exchange().expectStatus().isCreated()
                .expectBody(AppointmentLocation.class).returnResult().getResponseBody();
    }
}
