package es.upm.miw.apaw.domain.services.credentials;

import es.upm.miw.apaw.adapters.out.credentials.postgres.CredentialEntity;
import es.upm.miw.apaw.adapters.out.credentials.postgres.CredentialRepository;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.credentials.Credential;
import es.upm.miw.apaw.domain.model.credentials.CredentialType;
import es.upm.miw.apaw.domain.model.credentials.Verification;
import es.upm.miw.apaw.domain.model.credentials.VerificationPatch;
import es.upm.miw.apaw.domain.model.credentials.VerificationStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.VerificationSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class VerificationServiceIT {

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private CredentialRepository credentialRepository;

    @Test
    void testReadSeeder() {
        assertThat(this.verificationService.read(ID_0))
                .usingRecursiveComparison()
                .isEqualTo(VERIFICATION_0);
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();

        assertThatThrownBy(() -> this.verificationService.read(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void testFindAllAllowsAdditionalVerifications() {
        Verification extra = this.createVerification();

        List<Verification> verifications = this.verificationService.findAll();

        assertThat(verifications)
                .extracting(Verification::getId)
                .contains(ID_0, ID_1, ID_2, ID_3, ID_4, extra.getId());

        assertThat(verifications)
                .extracting(Verification::getCreatedAt)
                .containsSubsequence(
                        VERIFICATION_0.getCreatedAt(),
                        VERIFICATION_1.getCreatedAt(),
                        VERIFICATION_2.getCreatedAt(),
                        VERIFICATION_3.getCreatedAt(),
                        VERIFICATION_4.getCreatedAt());

        assertThat(this.verificationService.findAll())
                .extracting(Verification::getId)
                .containsExactlyElementsOf(
                        verifications.stream()
                                .map(Verification::getId)
                                .toList());
    }

    @Test
    void testCreate() {
        Verification verification = this.createVerification();

        Verification stored = this.verificationService.read(verification.getId());

        assertThat(stored)
                .usingRecursiveComparison()
                .ignoringFields("createdAt")
                .isEqualTo(verification);

        assertThat(stored.getCreatedAt()).isNotNull();
        assertThat(stored.getVerificationStatus())
                .isEqualTo(VerificationStatus.PENDING);
    }

    @Test
    void testUpdateReplacesMutableFields() {
        Verification original = this.createVerification();

        Verification storedBeforeUpdate =
                this.verificationService.read(original.getId());

        Verification replacement = Verification.builder()
                .method("UPDATED_METHOD")
                .name("Updated verification")
                .notes("Updated notes")
                .score(new BigDecimal("90.00"))
                .verifiedAt(LocalDateTime.of(2025, 7, 1, 10, 0))
                .verificationStatus(VerificationStatus.VERIFIED)
                .build();

        this.verificationService.update(original.getId(), replacement);

        Verification updated =
                this.verificationService.read(original.getId());

        assertThat(updated.getVerifiedAt())
                .isEqualTo(replacement.getVerifiedAt());
        assertThat(updated.getMethod())
                .isEqualTo(replacement.getMethod());
        assertThat(updated.getName())
                .isEqualTo(replacement.getName());
        assertThat(updated.getNotes())
                .isEqualTo(replacement.getNotes());
        assertThat(updated.getScore())
                .isEqualTo(replacement.getScore());
        assertThat(updated.getVerificationStatus())
                .isEqualTo(replacement.getVerificationStatus());

        assertThat(updated.getId())
                .isEqualTo(storedBeforeUpdate.getId());
        assertThat(updated.getCreatedAt())
                .isEqualTo(storedBeforeUpdate.getCreatedAt());
    }

    @Test
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();

        assertThatThrownBy(() -> this.verificationService.update(id, VERIFICATION_0))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void testDelete() {
        Verification verification = this.createVerification();

        this.verificationService.delete(verification.getId());

        assertThatThrownBy(() ->
                this.verificationService.read(verification.getId()))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteMissingVerification() {
        UUID id = UUID.randomUUID();

        this.verificationService.delete(id);

        assertThatThrownBy(() ->
                this.verificationService.read(id))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteReferencedVerification() {
        Verification verification = this.createVerification();

        Credential credential = Credential.builder()
                .id(UUID.randomUUID())
                .number("IT-" + UUID.randomUUID())
                .registryCode("REG-" + UUID.randomUUID())
                .authority("Test Authority")
                .issueDate(LocalDate.of(2025, 1, 1))
                .renewalCount(0)
                .renewable(true)
                .credentialType(CredentialType.CERTIFICATION)
                .user(UserSnapshot.builder()
                        .id(UUID.randomUUID())
                        .build())
                .verifications(List.of(verification))
                .build();

        CredentialEntity credentialEntity = new CredentialEntity(credential);
        this.credentialRepository.saveAndFlush(credentialEntity);

        assertThatThrownBy(() ->
                this.verificationService.delete(verification.getId()))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(verification.getId().toString());

        assertThat(this.verificationService.read(verification.getId()).getId())
                .isEqualTo(verification.getId());

        assertThat(this.credentialRepository
                .existsByVerificationsId(verification.getId()))
                .isTrue();
    }

    @Test
    void testPatch() {
        Verification verification = this.createVerification();

        Verification storedBeforePatch =
                this.verificationService.read(verification.getId());

        VerificationPatch patch = new VerificationPatch(
                null,
                "PATCH_METHOD",
                null,
                "Patched notes",
                null,
                VerificationStatus.VERIFIED);

        this.verificationService.patch(verification.getId(), patch);

        Verification updated =
                this.verificationService.read(verification.getId());

        assertThat(updated.getMethod())
                .isEqualTo("PATCH_METHOD");
        assertThat(updated.getNotes())
                .isEqualTo("Patched notes");
        assertThat(updated.getVerificationStatus())
                .isEqualTo(VerificationStatus.VERIFIED);

        assertThat(updated.getName())
                .isEqualTo(storedBeforePatch.getName());
        assertThat(updated.getScore())
                .isEqualTo(storedBeforePatch.getScore());
        assertThat(updated.getVerifiedAt())
                .isEqualTo(storedBeforePatch.getVerifiedAt());
        assertThat(updated.getId())
                .isEqualTo(storedBeforePatch.getId());
        assertThat(updated.getCreatedAt())
                .isEqualTo(storedBeforePatch.getCreatedAt());
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

        assertThatThrownBy(() ->
                this.verificationService.patch(id, patch))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    private Verification createVerification() {
        return this.verificationService.create(
                Verification.builder()
                        .createdAt(LocalDateTime.of(2025, 7, 10, 10, 0))
                        .verifiedAt(LocalDateTime.of(2025, 7, 11, 10, 0))
                        .method("IT_METHOD")
                        .name("IT verification " + UUID.randomUUID())
                        .notes("Original notes")
                        .score(new BigDecimal("75.00"))
                        .build());
    }
}