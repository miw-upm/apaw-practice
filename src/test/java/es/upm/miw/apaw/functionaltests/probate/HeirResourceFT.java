package es.upm.miw.apaw.functionaltests.probate;

import es.upm.miw.apaw.adapters.in.probate.HeirResource;
import es.upm.miw.apaw.domain.model.probate.Heir;
import es.upm.miw.apaw.domain.model.probate.HeirStatus;
import es.upm.miw.apaw.domain.model.probate.HeirUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class HeirResourceFT {
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
        Heir heir = Heir.builder()
                .fullName("Functional Heir")
                .nationalId("NID-" + UUID.randomUUID())
                .birthDate(LocalDate.of(1990, 1, 1))
                .sharePercentage(new BigDecimal("25.00"))
                .build();
        this.restTestClient.post().uri(HeirResource.HEIRS)
                .body(heir)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Heir.class)
                .value(body -> {
                    assertThat(body.getId()).isNotNull();
                    assertThat(body.getNationalId()).isEqualTo(heir.getNationalId());
                });
    }

    @Test
    void testRead() {
        Heir heir = this.createHeir();
        this.restTestClient.get().uri(HeirResource.HEIRS + "/" + heir.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Heir.class)
                .value(body -> assertThat(body).usingRecursiveComparison().isEqualTo(heir));
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        this.restTestClient.get().uri(HeirResource.HEIRS + "/" + id)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testFindAll() {
        this.restTestClient.get().uri(HeirResource.HEIRS)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Heir[].class)
                .value(body -> assertThat(body).isNotEmpty());
    }

    @Test
    void testUpdate() {
        Heir heir = this.createHeir();
        this.restTestClient.put().uri(HeirResource.HEIRS + "/" + heir.getId())
                .body(Heir.builder()
                        .fullName("Updated Heir")
                        .nationalId(heir.getNationalId())
                        .birthDate(heir.getBirthDate())
                        .sharePercentage(new BigDecimal("75.00"))
                        .heirStatus(HeirStatus.ACCEPTED)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Heir.class)
                .value(body -> assertThat(body).extracting(Heir::getFullName).isEqualTo("Updated Heir"));
    }

    @Test
    void testPatch() {
        Heir heir = this.createHeir();
        this.restTestClient.patch().uri(HeirResource.HEIRS + "/" + heir.getId())
                .body(new HeirUpdate(null, null, null, null, HeirStatus.NOTIFIED, null))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Heir.class)
                .value(body -> assertThat(body).extracting(Heir::getHeirStatus).isEqualTo(HeirStatus.NOTIFIED));
    }

    @Test
    void testDelete() {
        Heir heir = this.createHeir();
        this.restTestClient.delete().uri(HeirResource.HEIRS + "/" + heir.getId())
                .exchange()
                .expectStatus().isNoContent();
        this.restTestClient.get().uri(HeirResource.HEIRS + "/" + heir.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    private Heir createHeir() {
        Heir heir = Heir.builder()
                .fullName("FT Heir " + UUID.randomUUID())
                .nationalId("NID-" + UUID.randomUUID())
                .birthDate(LocalDate.of(1985, 6, 15))
                .sharePercentage(new BigDecimal("50.00"))
                .build();
        return this.restTestClient.post().uri(HeirResource.HEIRS)
                .body(heir)
                .exchange().expectStatus().isCreated()
                .expectBody(Heir.class).returnResult().getResponseBody();
    }
}
