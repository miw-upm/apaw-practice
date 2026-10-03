package es.upm.miw.apaw.functionaltests.immigrationissues;

import es.upm.miw.apaw.adapters.in.immigrationissues.LawBasisResource;
import es.upm.miw.apaw.domain.model.immigrationissues.LawBasis;
import es.upm.miw.apaw.domain.model.immigrationissues.LawBasisUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.ImmigrationIssuesSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class LawBasisResourceFT {

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
        LawBasis lawBasis = this.newLawBasis();
        this.restTestClient.post().uri(LawBasisResource.LAW_BASES)
                .body(lawBasis)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(LawBasis.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isNotNull();
                    assertThat(body.getActive()).isTrue();
                    assertThat(body.getLawCode()).isEqualTo(lawBasis.getLawCode());
                    assertThat(body.getLawName()).isEqualTo(lawBasis.getLawName());
                    assertThat(body.getArticleNumber()).isEqualTo(lawBasis.getArticleNumber());
                    assertThat(body.getPublishedOn()).isEqualTo(lawBasis.getPublishedOn());
                });
    }

    @Test
    void testCreateBlankLawCode() {
        this.restTestClient.post().uri(LawBasisResource.LAW_BASES)
                .body(LawBasis.builder().lawCode(" ").lawName("Law").articleNumber(1).build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateBlankLawName() {
        this.restTestClient.post().uri(LawBasisResource.LAW_BASES)
                .body(LawBasis.builder().lawCode("ES-FT-1").lawName(" ").articleNumber(1).build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateMissingArticleNumber() {
        this.restTestClient.post().uri(LawBasisResource.LAW_BASES)
                .body(LawBasis.builder().lawCode("ES-FT-" + UUID.randomUUID()).lawName("Law").build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateMissingPublishedOn() {
        this.restTestClient.post().uri(LawBasisResource.LAW_BASES)
                .body(LawBasis.builder().lawCode("ES-FT-" + UUID.randomUUID())
                        .lawName("Law").articleNumber(1).build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateDuplicateLawCode() {
        LawBasis lawBasis = this.newLawBasis();
        lawBasis.setLawCode(LAW_BASIS_0.getLawCode());
        this.restTestClient.post().uri(LawBasisResource.LAW_BASES)
                .body(lawBasis)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testRead() {
        this.restTestClient.get().uri(LawBasisResource.LAW_BASES + "/" + ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LawBasis.class)
                .value(body -> assertThat(body).usingRecursiveComparison().isEqualTo(LAW_BASIS_0));
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        this.restTestClient.get().uri(LawBasisResource.LAW_BASES + "/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message")).contains(id.toString()));
    }

    @Test
    void testFindAll() {
        LawBasis extra = this.createLawBasis();
        this.restTestClient.get().uri(LawBasisResource.LAW_BASES)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LawBasis[].class)
                .value(body -> {
                    assertThat(body).extracting(LawBasis::getId)
                            .contains(ID_0, ID_1, ID_2, ID_3, ID_4, ID_5, extra.getId());
                    assertThat(body).extracting(LawBasis::getId)
                            .containsSubsequence(ID_0, ID_1, ID_2, ID_3, ID_4, ID_5);
                    assertThat(body).extracting(LawBasis::getLawCode).isSorted();
                });
    }

    @Test
    void testUpdate() {
        LawBasis lawBasis = this.createLawBasis();
        String newLawCode = "ES-FT-" + UUID.randomUUID();
        this.restTestClient.put().uri(LawBasisResource.LAW_BASES + "/" + lawBasis.getId())
                .body(LawBasis.builder().lawCode(newLawCode).lawName("Updated law")
                        .articleNumber(11).publishedOn(LocalDate.of(2025, 9, 1)).active(false).build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(LawBasis.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isEqualTo(lawBasis.getId());
                    assertThat(body.getLawCode()).isEqualTo(newLawCode);
                    assertThat(body.getLawName()).isEqualTo("Updated law");
                    assertThat(body.getArticleNumber()).isEqualTo(11);
                    assertThat(body.getActive()).isFalse();
                });
    }

    @Test
    void testUpdateNotFound() {
        this.restTestClient.put().uri(LawBasisResource.LAW_BASES + "/" + UUID.randomUUID())
                .body(this.newLawBasis())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateDuplicateLawCode() {
        LawBasis lawBasis = this.createLawBasis();
        this.restTestClient.put().uri(LawBasisResource.LAW_BASES + "/" + lawBasis.getId())
                .body(LawBasis.builder().lawCode(LAW_BASIS_0.getLawCode())
                        .lawName("Other").articleNumber(1)
                        .publishedOn(LocalDate.of(2025, 1, 1)).build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdateBlankLawName() {
        LawBasis lawBasis = this.createLawBasis();
        this.restTestClient.put().uri(LawBasisResource.LAW_BASES + "/" + lawBasis.getId())
                .body(LawBasis.builder().lawCode(lawBasis.getLawCode()).lawName(" ")
                        .articleNumber(1).publishedOn(LocalDate.of(2025, 1, 1)).build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testPatch() {
        LawBasis lawBasis = this.createLawBasis();
        this.restTestClient.patch().uri(LawBasisResource.LAW_BASES + "/" + lawBasis.getId())
                .body(new LawBasisUpdate(null, "Patched law", null, null, false))
                .exchange()
                .expectStatus().isOk()
                .expectBody(LawBasis.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isEqualTo(lawBasis.getId());
                    assertThat(body.getLawName()).isEqualTo("Patched law");
                    assertThat(body.getActive()).isFalse();
                    assertThat(body.getLawCode()).isEqualTo(lawBasis.getLawCode());
                    assertThat(body.getArticleNumber()).isEqualTo(lawBasis.getArticleNumber());
                    assertThat(body.getPublishedOn()).isEqualTo(lawBasis.getPublishedOn());
                });
    }

    @Test
    void testPatchEmptyBodyChangesNothing() {
        LawBasis lawBasis = this.createLawBasis();
        this.restTestClient.patch().uri(LawBasisResource.LAW_BASES + "/" + lawBasis.getId())
                .body(new LawBasisUpdate(null, null, null, null, null))
                .exchange()
                .expectStatus().isOk();
        this.restTestClient.get().uri(LawBasisResource.LAW_BASES + "/" + lawBasis.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(LawBasis.class)
                .value(body -> assertThat(body).usingRecursiveComparison().isEqualTo(lawBasis));
    }

    @Test
    void testPatchNotFound() {
        this.restTestClient.patch().uri(LawBasisResource.LAW_BASES + "/" + UUID.randomUUID())
                .body(new LawBasisUpdate(null, "Patched", null, null, null))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchDuplicateLawCode() {
        LawBasis lawBasis = this.createLawBasis();
        this.restTestClient.patch().uri(LawBasisResource.LAW_BASES + "/" + lawBasis.getId())
                .body(new LawBasisUpdate(LAW_BASIS_0.getLawCode(), null, null, null, null))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testDelete() {
        LawBasis lawBasis = this.createLawBasis();
        this.restTestClient.delete().uri(LawBasisResource.LAW_BASES + "/" + lawBasis.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody().isEmpty();
        this.restTestClient.get().uri(LawBasisResource.LAW_BASES + "/" + lawBasis.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteNotFound() {
        this.restTestClient.delete().uri(LawBasisResource.LAW_BASES + "/" + UUID.randomUUID())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteReferencedLawBasis() {
        this.restTestClient.delete().uri(LawBasisResource.LAW_BASES + "/" + ID_0)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT)
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message")).contains(ID_0.toString()));
        this.restTestClient.get().uri(LawBasisResource.LAW_BASES + "/" + ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LawBasis.class)
                .value(body -> assertThat(body).usingRecursiveComparison().isEqualTo(LAW_BASIS_0));
    }

    private LawBasis newLawBasis() {
        return LawBasis.builder()
                .lawCode("ES-FT-" + UUID.randomUUID())
                .lawName("FT law basis")
                .articleNumber(3)
                .publishedOn(LocalDate.of(2025, 6, 1))
                .build();
    }

    private LawBasis createLawBasis() {
        return this.restTestClient.post().uri(LawBasisResource.LAW_BASES)
                .body(this.newLawBasis())
                .exchange().expectStatus().isCreated()
                .expectBody(LawBasis.class).returnResult().getResponseBody();
    }
}