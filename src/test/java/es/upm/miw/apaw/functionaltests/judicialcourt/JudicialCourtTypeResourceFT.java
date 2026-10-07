package es.upm.miw.apaw.functionaltests.judicialcourt;

import es.upm.miw.apaw.adapters.in.judicialcourt.JudicialCourtTypeResource;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtType;
import es.upm.miw.apaw.domain.model.judicialcourt.JudicialCourtTypeUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.Map;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.JudicialCourtTypeSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class JudicialCourtTypeResourceFT {
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
        this.restTestClient.get().uri(JudicialCourtTypeResource.JUDICIAL_COURT_TYPES + "/" + ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(JudicialCourtType.class)
                .value(body -> assertThat(body).usingRecursiveComparison().isEqualTo(TYPE_0));
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        this.restTestClient.get().uri(JudicialCourtTypeResource.JUDICIAL_COURT_TYPES + "/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message")).contains(id.toString()));
    }

    @Test
    void testFindAllAllowsAdditionalTypes() {
        JudicialCourtType extra = this.createType();
        this.restTestClient.get().uri(JudicialCourtTypeResource.JUDICIAL_COURT_TYPES)
                .exchange()
                .expectStatus().isOk()
                .expectBody(JudicialCourtType[].class)
                .value(body -> assertThat(body).extracting(JudicialCourtType::getId)
                        .contains(ID_0, ID_1, ID_2, ID_3, ID_4, extra.getId()));
    }

    @Test
    void testCreateDefaultsToActiveTrue() {
        JudicialCourtType judicialCourtType = this.createType();
        assertThat(judicialCourtType.getActive()).isTrue();
    }

    @Test
    void testCreateBlankName() {
        this.restTestClient.post().uri(JudicialCourtTypeResource.JUDICIAL_COURT_TYPES)
                .body(JudicialCourtType.builder().name(" ").code(this.uniqueCode()).jurisdiction("Penal").build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateDuplicateName() {
        this.restTestClient.post().uri(JudicialCourtTypeResource.JUDICIAL_COURT_TYPES)
                .body(JudicialCourtType.builder().name(TYPE_0.getName()).description("Duplicate name").code(this.uniqueCode()).jurisdiction("Paz").build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testCreateDuplicateCode() {
        this.restTestClient.post().uri(JudicialCourtTypeResource.JUDICIAL_COURT_TYPES)
                .body(JudicialCourtType.builder().name("Nuevo tribunal").description("Duplicate code").code(TYPE_0.getCode()).jurisdiction("Paz").build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdate() {
        JudicialCourtType judicialCourtType = this.createType();

        this.restTestClient.put().uri(JudicialCourtTypeResource.JUDICIAL_COURT_TYPES + "/" + judicialCourtType.getId())
                .body(JudicialCourtType.builder()
                        .name("Updated type")
                        .description("Updated description")
                        .code(this.uniqueCode())
                        .jurisdiction("Civil")
                        .active(false)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(JudicialCourtType.class)
                .value(body -> {
                    assertThat(body.getId()).isEqualTo(judicialCourtType.getId());
                    assertThat(body.getName()).isEqualTo("Updated type");
                    assertThat(body.getDescription()).isEqualTo("Updated description");
                    assertThat(body.getJurisdiction()).isEqualTo("Civil");
                    assertThat(body.getActive()).isFalse();
                });
    }

    @Test
    void testUpdateNotFound() {
        this.restTestClient.put().uri(JudicialCourtTypeResource.JUDICIAL_COURT_TYPES + "/" + UUID.randomUUID())
                .body(JudicialCourtType.builder().name("Missing type").code(this.uniqueCode()).jurisdiction("Penal").build())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateDuplicateName() {
        JudicialCourtType judicialCourtType = this.createType();
        this.restTestClient.put().uri(JudicialCourtTypeResource.JUDICIAL_COURT_TYPES + "/" + judicialCourtType.getId())
                .body(JudicialCourtType.builder().name(TYPE_0.getName()).description("bad").code(this.uniqueCode()).jurisdiction("Paz").active(true).build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testPatch() {
        JudicialCourtType judicialCourtType = this.createType();
        JudicialCourtTypeUpdate update = new JudicialCourtTypeUpdate(
                "Patched name", "Patched description", null, "Mercantil", false);

        this.restTestClient.patch().uri(JudicialCourtTypeResource.JUDICIAL_COURT_TYPES + "/" + judicialCourtType.getId())
                .body(update)
                .exchange()
                .expectStatus().isOk()
                .expectBody(JudicialCourtType.class)
                .value(body -> {
                    assertThat(body.getId()).isEqualTo(judicialCourtType.getId());
                    assertThat(body.getName()).isEqualTo("Patched name");
                    assertThat(body.getDescription()).isEqualTo("Patched description");
                    assertThat(body.getCode()).isEqualTo(judicialCourtType.getCode());
                    assertThat(body.getJurisdiction()).isEqualTo("Mercantil");
                    assertThat(body.getActive()).isFalse();
                });
    }

    @Test
    void testPatchNotFound() {
        this.restTestClient.patch().uri(JudicialCourtTypeResource.JUDICIAL_COURT_TYPES + "/" + UUID.randomUUID())
                .body(new JudicialCourtTypeUpdate("New name", "desc", "CODE", "Penal", true))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchDuplicateName() {
        JudicialCourtType judicialCourtType = this.createType();
        this.restTestClient.patch().uri(JudicialCourtTypeResource.JUDICIAL_COURT_TYPES + "/" + judicialCourtType.getId())
                .body(new JudicialCourtTypeUpdate(TYPE_0.getName(), "desc", judicialCourtType.getCode(), "Paz", true))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testDelete() {
        JudicialCourtType judicialCourtType = this.createType();
        this.restTestClient.delete().uri(JudicialCourtTypeResource.JUDICIAL_COURT_TYPES + "/" + judicialCourtType.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody().isEmpty();

        this.restTestClient.get().uri(JudicialCourtTypeResource.JUDICIAL_COURT_TYPES + "/" + judicialCourtType.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    private JudicialCourtType createType() {
        return this.restTestClient.post().uri(JudicialCourtTypeResource.JUDICIAL_COURT_TYPES)
                .body(JudicialCourtType.builder()
                        .name("FT tribunal " + UUID.randomUUID())
                        .description("Generated for FT")
                        .code(this.uniqueCode())
                        .jurisdiction("Paz")
                        .build())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(JudicialCourtType.class)
                .returnResult().getResponseBody();
    }

    private String uniqueCode() {
        return "JCT" + UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
    }
}
