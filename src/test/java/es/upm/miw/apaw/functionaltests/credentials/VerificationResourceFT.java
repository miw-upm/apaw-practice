package es.upm.miw.apaw.functionaltests.credentials;

import es.upm.miw.apaw.adapters.in.credentials.VerificationResource;
import es.upm.miw.apaw.domain.model.credentials.Verification;
import es.upm.miw.apaw.domain.model.credentials.VerificationPatch;
import es.upm.miw.apaw.domain.model.credentials.VerificationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.VerificationSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class VerificationResourceFT {

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
        this.restTestClient.get()
                .uri(VerificationResource.VERIFICATIONS + "/" + ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Verification.class)
                .value(body -> assertThat(body)
                        .usingRecursiveComparison()
                        .isEqualTo(VERIFICATION_0));
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();

        this.restTestClient.get()
                .uri(VerificationResource.VERIFICATIONS + "/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message"))
                        .contains(id.toString()));
    }

    @Test
    void testFindAll() {
        this.restTestClient.get()
                .uri(VerificationResource.VERIFICATIONS)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Verification[].class)
                .value(body -> assertThat(body)
                        .extracting(Verification::getId)
                        .containsSubsequence(
                                ID_0,
                                ID_1,
                                ID_2,
                                ID_3,
                                ID_4));
    }

    @Test
    void testCreateDefaultsToPending() {
        this.restTestClient.post()
                .uri(VerificationResource.VERIFICATIONS)
                .body(Verification.builder()
                        .method("FT_METHOD")
                        .name("FT verification")
                        .build())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Verification.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isNotNull();
                    assertThat(body.getCreatedAt()).isNotNull();
                    assertThat(body.getVerificationStatus())
                            .isEqualTo(VerificationStatus.PENDING);
                });
    }

    @Test
    void testUpdate() {
        Verification verification = this.createVerification();

        Verification storedBeforeUpdate = this.read(verification.getId());

        Verification replacement = Verification.builder()
                .method("UPDATED_METHOD")
                .name("Updated verification")
                .notes("Updated notes")
                .score(new BigDecimal("90.00"))
                .verifiedAt(LocalDateTime.of(2025, 8, 2, 10, 0))
                .verificationStatus(VerificationStatus.VERIFIED)
                .build();

        this.restTestClient.put()
                .uri(VerificationResource.VERIFICATIONS + "/" + verification.getId())
                .body(replacement)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Verification.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId())
                            .isEqualTo(storedBeforeUpdate.getId());
                    assertThat(body.getCreatedAt())
                            .isEqualTo(storedBeforeUpdate.getCreatedAt());
                    assertThat(body.getMethod())
                            .isEqualTo(replacement.getMethod());
                    assertThat(body.getName())
                            .isEqualTo(replacement.getName());
                    assertThat(body.getNotes())
                            .isEqualTo(replacement.getNotes());
                    assertThat(body.getScore())
                            .isEqualTo(replacement.getScore());
                    assertThat(body.getVerifiedAt())
                            .isEqualTo(replacement.getVerifiedAt());
                    assertThat(body.getVerificationStatus())
                            .isEqualTo(replacement.getVerificationStatus());
                });
    }

    @Test
    void testUpdateNotFound() {
        this.restTestClient.put()
                .uri(VerificationResource.VERIFICATIONS + "/" + UUID.randomUUID())
                .body(Verification.builder()
                        .method("Missing")
                        .build())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDelete() {
        Verification verification = this.createVerification();

        this.restTestClient.delete()
                .uri(VerificationResource.VERIFICATIONS + "/" + verification.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody().isEmpty();

        this.restTestClient.get()
                .uri(VerificationResource.VERIFICATIONS + "/" + verification.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatch() {
        Verification verification = this.createVerification();

        Verification storedBeforePatch = this.read(verification.getId());

        VerificationPatch patch = new VerificationPatch(
                null,
                "PATCH_METHOD",
                null,
                "Patched notes",
                null,
                VerificationStatus.VERIFIED);

        this.restTestClient.patch()
                .uri(VerificationResource.VERIFICATIONS + "/" + verification.getId())
                .body(patch)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Verification.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId())
                            .isEqualTo(storedBeforePatch.getId());
                    assertThat(body.getCreatedAt())
                            .isEqualTo(storedBeforePatch.getCreatedAt());

                    assertThat(body.getMethod())
                            .isEqualTo("PATCH_METHOD");
                    assertThat(body.getNotes())
                            .isEqualTo("Patched notes");
                    assertThat(body.getVerificationStatus())
                            .isEqualTo(VerificationStatus.VERIFIED);

                    assertThat(body.getName())
                            .isEqualTo(storedBeforePatch.getName());
                    assertThat(body.getScore())
                            .isEqualTo(storedBeforePatch.getScore());
                    assertThat(body.getVerifiedAt())
                            .isEqualTo(storedBeforePatch.getVerifiedAt());
                });
    }

    @Test
    void testPatchNotFound() {
        UUID id = UUID.randomUUID();

        VerificationPatch patch = new VerificationPatch(
                null,
                "PATCH_METHOD",
                null,
                null,
                null,
                null);

        this.restTestClient.patch()
                .uri(VerificationResource.VERIFICATIONS + "/" + id)
                .body(patch)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchBlankMethod() {
        Verification verification = this.createVerification();

        VerificationPatch patch = new VerificationPatch(
                null,
                " ",
                null,
                null,
                null,
                null);

        this.restTestClient.patch()
                .uri(VerificationResource.VERIFICATIONS + "/" + verification.getId())
                .body(patch)
                .exchange()
                .expectStatus().isBadRequest();
    }

    private Verification createVerification() {
        return this.restTestClient.post()
                .uri(VerificationResource.VERIFICATIONS)
                .body(Verification.builder()
                        .verifiedAt(LocalDateTime.of(2025, 8, 2, 10, 0))
                        .method("FT_METHOD")
                        .name("FT verification " + UUID.randomUUID())
                        .notes("Original notes")
                        .score(new BigDecimal("75.00"))
                        .build())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Verification.class)
                .returnResult()
                .getResponseBody();
    }

    private Verification read(UUID id) {
        return this.restTestClient.get()
                .uri(VerificationResource.VERIFICATIONS + "/" + id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Verification.class)
                .returnResult()
                .getResponseBody();
    }
}