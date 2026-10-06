package es.upm.miw.apaw.domain.services.credentials;

import es.upm.miw.apaw.adapters.out.credentials.postgres.CredentialEntity;
import es.upm.miw.apaw.adapters.out.credentials.postgres.CredentialRepository;
import es.upm.miw.apaw.adapters.out.credentials.postgres.VerificationEntity;
import es.upm.miw.apaw.adapters.out.credentials.postgres.VerificationRepository;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.credentials.Credential;
import es.upm.miw.apaw.domain.model.credentials.CredentialFindCriteria;
import es.upm.miw.apaw.domain.model.credentials.CredentialType;
import es.upm.miw.apaw.domain.model.credentials.CreationCredential;
import es.upm.miw.apaw.domain.model.credentials.Verification;
import es.upm.miw.apaw.domain.model.credentials.VerificationStatus;
import es.upm.miw.apaw.domain.ports.out.credentials.VerificationGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.VerificationSeederForDev.ID_0;
import static es.upm.miw.apaw.config.seeders.VerificationSeederForDev.ID_1;
import static es.upm.miw.apaw.config.seeders.VerificationSeederForDev.ID_2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class CredentialServiceIT {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private VerificationRepository verificationRepository;

    @MockitoBean
    private VerificationGateway verificationGateway;

    @MockitoBean
    private UserFinder userFinder;

    @Test
    @Transactional
    void testCreate() {
        UserSnapshot user = this.user();

        CreationCredential creation = this.creation(
                "Credential " + UUID.randomUUID(),
                "REG-" + UUID.randomUUID(),
                user.getId(),
                List.of(ID_0, ID_1));

        when(this.userFinder.read(user.getId())).thenReturn(user);

        Verification firstVerification = Verification.builder()
                .id(ID_0)
                .build();
        Verification secondVerification = Verification.builder()
                .id(ID_1)
                .build();

        when(this.verificationGateway.read(ID_0))
                .thenReturn(Optional.of(firstVerification));
        when(this.verificationGateway.read(ID_1))
                .thenReturn(Optional.of(secondVerification));

        Credential credential = this.credentialService.create(creation);

        assertThat(credential.getId()).isNotNull();
        assertThat(credential.getNumber()).isEqualTo(creation.getNumber());
        assertThat(credential.getRegistryCode()).isEqualTo(creation.getRegistryCode());
        assertThat(credential.getAuthority()).isEqualTo(creation.getAuthority());
        assertThat(credential.getIssueDate()).isEqualTo(creation.getIssueDate());
        assertThat(credential.getExpirationDate()).isEqualTo(creation.getExpirationDate());
        assertThat(credential.getCredentialType()).isEqualTo(creation.getCredentialType());
        assertThat(credential.getRenewalCount()).isZero();
        assertThat(credential.getRenewable()).isTrue();
        assertThat(credential.getUser()).isEqualTo(user);
        assertThat(credential.getVerifications())
                .extracting(Verification::getId)
                .containsExactly(ID_0, ID_1);

        CredentialEntity entity = this.credentialRepository
                .findById(credential.getId())
                .orElseThrow();

        assertThat(entity.getNumber()).isEqualTo(creation.getNumber());
        assertThat(entity.getRegistryCode()).isEqualTo(creation.getRegistryCode());
        assertThat(entity.getAuthority()).isEqualTo(creation.getAuthority());
        assertThat(entity.getIssueDate()).isEqualTo(creation.getIssueDate());
        assertThat(entity.getCredentialType()).isEqualTo(creation.getCredentialType());
        assertThat(entity.getRenewalCount()).isZero();
        assertThat(entity.getRenewable()).isTrue();
        assertThat(entity.getUserId()).isEqualTo(user.getId());
        assertThat(entity.getVerifications())
                .extracting(VerificationEntity::getId)
                .containsExactly(ID_0, ID_1);

        verify(this.userFinder, times(1)).read(user.getId());
        verify(this.verificationGateway, times(1)).read(ID_0);
        verify(this.verificationGateway, times(1)).read(ID_1);
    }

    @Test
    @Transactional
    void testCreateWithoutVerifications() {
        UserSnapshot user = this.user();

        CreationCredential creation = this.creation(
                "Credential " + UUID.randomUUID(),
                "REG-" + UUID.randomUUID(),
                user.getId(),
                List.of());

        when(this.userFinder.read(user.getId())).thenReturn(user);

        Credential credential = this.credentialService.create(creation);

        assertThat(credential.getId()).isNotNull();
        assertThat(credential.getNumber()).isEqualTo(creation.getNumber());
        assertThat(credential.getRenewalCount()).isZero();
        assertThat(credential.getRenewable()).isTrue();
        assertThat(credential.getUser()).isEqualTo(user);
        assertThat(credential.getVerifications()).isEmpty();

        CredentialEntity entity = this.credentialRepository
                .findById(credential.getId())
                .orElseThrow();

        assertThat(entity.getVerifications()).isEmpty();
        assertThat(entity.getUserId()).isEqualTo(user.getId());

        verify(this.userFinder, times(1)).read(user.getId());
    }

    @Test
    @Transactional
    void testCreateDuplicateNumber() {
        UserSnapshot user = this.user();

        String number = "Credential " + UUID.randomUUID();

        CreationCredential first = this.creation(
                number,
                "REG-" + UUID.randomUUID(),
                user.getId(),
                List.of());

        when(this.userFinder.read(user.getId())).thenReturn(user);

        this.credentialService.create(first);

        CreationCredential duplicate = this.creation(
                number,
                "REG-" + UUID.randomUUID(),
                user.getId(),
                List.of());

        assertThatThrownBy(() -> this.credentialService.create(duplicate))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(number);
    }

    @Test
    @Transactional
    void testCreateDuplicateRegistryCode() {
        UserSnapshot user = this.user();

        String registryCode = "REG-" + UUID.randomUUID();

        CreationCredential first = this.creation(
                "Credential " + UUID.randomUUID(),
                registryCode,
                user.getId(),
                List.of());

        when(this.userFinder.read(user.getId())).thenReturn(user);

        this.credentialService.create(first);

        CreationCredential duplicate = this.creation(
                "Credential " + UUID.randomUUID(),
                registryCode,
                user.getId(),
                List.of());

        assertThatThrownBy(() -> this.credentialService.create(duplicate))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(registryCode);
    }

    @Test
    void testCreateVerificationNotFound() {
        UUID verificationId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        CreationCredential creation = this.creation(
                "Credential " + UUID.randomUUID(),
                "REG-" + UUID.randomUUID(),
                userId,
                List.of(verificationId));

        when(this.verificationGateway.read(verificationId))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.credentialService.create(creation))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(verificationId.toString());

        verify(this.userFinder, never()).read(userId);
    }

    @Test
    void testCreateUserNotFound() {
        UserSnapshot user = this.user();

        CreationCredential creation = this.creation(
                "Credential " + UUID.randomUUID(),
                "REG-" + UUID.randomUUID(),
                user.getId(),
                List.of(ID_0));

        Verification verification = Verification.builder()
                .id(ID_0)
                .build();

        when(this.verificationGateway.read(ID_0))
                .thenReturn(Optional.of(verification));

        when(this.userFinder.read(user.getId()))
                .thenThrow(new NotFoundException(
                        "User id not found: " + user.getId()));

        assertThatThrownBy(() -> this.credentialService.create(creation))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(user.getId().toString());

        verify(this.userFinder, times(1)).read(user.getId());
    }

    @Test
    @Transactional
    void testFindAllAllowsAdditionalCredentials() {
        UUID firstUserId = UUID.randomUUID();
        UUID secondUserId = UUID.randomUUID();

        CredentialEntity first = this.saveCredential(
                CredentialType.LICENSE,
                LocalDate.of(2030, 1, 1),
                firstUserId);

        CredentialEntity second = this.saveCredential(
                CredentialType.REGISTRATION,
                LocalDate.of(2030, 1, 1),
                secondUserId);

        this.mockUsers(Map.of(
                firstUserId, "first-" + UUID.randomUUID() + "@example.com",
                secondUserId, "second-" + UUID.randomUUID() + "@example.com"));

        List<Credential> credentials = this.credentialService.find(
                new CredentialFindCriteria());

        assertThat(credentials)
                .extracting(Credential::getId)
                .contains(first.getId(), second.getId());

        assertThat(this.credentialService.find(new CredentialFindCriteria()))
                .extracting(Credential::getId)
                .containsExactlyElementsOf(
                        credentials.stream()
                                .map(Credential::getId)
                                .toList());
    }

    @Test
    @Transactional
    void testFindByCredentialType() {
        UUID licenseUserId = UUID.randomUUID();
        UUID registrationUserId = UUID.randomUUID();

        CredentialEntity license = this.saveCredential(
                CredentialType.LICENSE,
                LocalDate.of(2030, 1, 1),
                licenseUserId);

        CredentialEntity registration = this.saveCredential(
                CredentialType.REGISTRATION,
                LocalDate.of(2030, 1, 1),
                registrationUserId);

        this.mockUsers(Map.of());

        List<Credential> credentials = this.credentialService.find(
                CredentialFindCriteria.builder()
                        .credentialType(CredentialType.LICENSE)
                        .build());

        assertThat(credentials)
                .extracting(Credential::getId)
                .contains(license.getId())
                .doesNotContain(registration.getId());
    }

    @Test
    @Transactional
    void testFindByExpired() {
        UUID expiredUserId = UUID.randomUUID();
        UUID validUserId = UUID.randomUUID();
        UUID noExpirationUserId = UUID.randomUUID();

        CredentialEntity expired = this.saveCredential(
                CredentialType.CERTIFICATION,
                LocalDate.of(2020, 1, 1),
                expiredUserId);

        CredentialEntity valid = this.saveCredential(
                CredentialType.CERTIFICATION,
                LocalDate.of(2099, 1, 1),
                validUserId);

        CredentialEntity withoutExpiration = this.saveCredential(
                CredentialType.CERTIFICATION,
                null,
                noExpirationUserId);

        this.mockUsers(Map.of());

        List<Credential> credentials = this.credentialService.find(
                CredentialFindCriteria.builder()
                        .expired(true)
                        .build());

        assertThat(credentials)
                .extracting(Credential::getId)
                .contains(expired.getId())
                .doesNotContain(valid.getId(), withoutExpiration.getId());
    }

    @Test
    @Transactional
    void testFindByNotExpired() {
        UUID expiredUserId = UUID.randomUUID();
        UUID validUserId = UUID.randomUUID();
        UUID noExpirationUserId = UUID.randomUUID();

        CredentialEntity expired = this.saveCredential(
                CredentialType.CERTIFICATION,
                LocalDate.of(2020, 1, 1),
                expiredUserId);

        CredentialEntity valid = this.saveCredential(
                CredentialType.CERTIFICATION,
                LocalDate.of(2099, 1, 1),
                validUserId);

        CredentialEntity withoutExpiration = this.saveCredential(
                CredentialType.CERTIFICATION,
                null,
                noExpirationUserId);

        this.mockUsers(Map.of());

        List<Credential> credentials = this.credentialService.find(
                CredentialFindCriteria.builder()
                        .expired(false)
                        .build());

        assertThat(credentials)
                .extracting(Credential::getId)
                .contains(valid.getId(), withoutExpiration.getId())
                .doesNotContain(expired.getId());
    }

    @Test
    @Transactional
    void testFindByVerificationStatus() {
        UUID pendingUserId = UUID.randomUUID();
        UUID verifiedUserId = UUID.randomUUID();

        CredentialEntity pending = this.saveCredential(
                CredentialType.ACCREDITATION,
                LocalDate.of(2099, 1, 1),
                pendingUserId,
                ID_0);

        CredentialEntity verified = this.saveCredential(
                CredentialType.ACCREDITATION,
                LocalDate.of(2099, 1, 1),
                verifiedUserId,
                ID_1);

        this.mockUsers(Map.of());

        List<Credential> credentials = this.credentialService.find(
                CredentialFindCriteria.builder()
                        .verificationStatus(VerificationStatus.VERIFIED)
                        .build());

        assertThat(credentials)
                .extracting(Credential::getId)
                .contains(verified.getId())
                .doesNotContain(pending.getId());
    }

    @Test
    @Transactional
    void testFindByUserEmail() {
        UUID firstUserId = UUID.randomUUID();
        UUID secondUserId = UUID.randomUUID();

        String firstEmail = "find-" + UUID.randomUUID() + "@example.com";
        String secondEmail = "find-" + UUID.randomUUID() + "@example.com";

        CredentialEntity first = this.saveCredential(
                CredentialType.REGISTRATION,
                LocalDate.of(2099, 1, 1),
                firstUserId);

        CredentialEntity second = this.saveCredential(
                CredentialType.REGISTRATION,
                LocalDate.of(2099, 1, 1),
                secondUserId);

        this.mockUsers(Map.of(
                firstUserId, firstEmail,
                secondUserId, secondEmail));

        List<Credential> credentials = this.credentialService.find(
                CredentialFindCriteria.builder()
                        .credentialType(CredentialType.REGISTRATION)
                        .userEmail(firstEmail)
                        .build());

        assertThat(credentials)
                .extracting(Credential::getId)
                .contains(first.getId())
                .doesNotContain(second.getId());

        verify(this.userFinder, times(1)).findByIds(anySet());
    }

    @Test
    @Transactional
    void testFindThrowsWhenUserCannotBeResolved() {
        UUID userId = UUID.randomUUID();

        this.saveCredential(
                CredentialType.CERTIFICATION,
                LocalDate.of(2099, 1, 1),
                userId);

        when(this.userFinder.findByIds(anySet()))
                .thenReturn(List.of());

        assertThatThrownBy(() ->
                this.credentialService.find(
                        CredentialFindCriteria.builder()
                                .credentialType(CredentialType.CERTIFICATION)
                                .build()))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(userId.toString());
    }

    private UserSnapshot user() {
        return UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .mobile("600000100")
                .firstName("cliente0")
                .build();
    }

    private CreationCredential creation(
            String number,
            String registryCode,
            UUID userId,
            List<UUID> verificationIds) {
        return CreationCredential.builder()
                .number(number)
                .registryCode(registryCode)
                .authority("Test Authority")
                .issueDate(LocalDate.of(2025, 1, 1))
                .expirationDate(LocalDate.of(2030, 1, 1))
                .credentialType(CredentialType.CERTIFICATION)
                .verificationIds(verificationIds)
                .userId(userId)
                .build();
    }

    private CredentialEntity saveCredential(
            CredentialType credentialType,
            LocalDate expirationDate,
            UUID userId,
            UUID... verificationIds) {

        List<VerificationEntity> verifications = List.of(verificationIds).stream()
                .map(this.verificationRepository::getReferenceById)
                .toList();

        CredentialEntity entity = CredentialEntity.builder()
                .id(UUID.randomUUID())
                .number("FIND-" + UUID.randomUUID())
                .authority("Test Authority")
                .issueDate(LocalDate.of(2025, 1, 1))
                .expirationDate(expirationDate)
                .renewalCount(0)
                .renewable(true)
                .credentialType(credentialType)
                .verifications(verifications)
                .userId(userId)
                .build();

        return this.credentialRepository.saveAndFlush(entity);
    }

    private void mockUsers(Map<UUID, String> emails) {
        when(this.userFinder.findByIds(anySet()))
                .thenAnswer(invocation -> {
                    Set<UUID> ids = invocation.getArgument(0);

                    return ids.stream()
                            .map(id -> UserSnapshot.builder()
                                    .id(id)
                                    .email(emails.getOrDefault(
                                            id,
                                            "other-" + id + "@example.com"))
                                    .build())
                            .toList();
                });
    }
}