package es.upm.miw.apaw.adapters.out.credentials.postgres;

import es.upm.miw.apaw.domain.model.credentials.CredentialType;
import es.upm.miw.apaw.domain.model.credentials.CredentialVerificationReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.VerificationSeederForDev.ID_0;
import static es.upm.miw.apaw.config.seeders.VerificationSeederForDev.ID_1;
import static es.upm.miw.apaw.config.seeders.VerificationSeederForDev.ID_2;
import static es.upm.miw.apaw.config.seeders.VerificationSeederForDev.ID_3;
import static es.upm.miw.apaw.config.seeders.VerificationSeederForDev.ID_4;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class CredentialRepositoryIT {

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private VerificationRepository verificationRepository;

    @Test
    @Transactional
    void testFindCredentialVerificationReport() {
        CredentialEntity credentialWithThreeVerifications = this.saveCredential(
                List.of(ID_0, ID_1, ID_3));

        CredentialEntity credentialWithTwoVerifications = this.saveCredential(
                List.of(ID_2, ID_4));

        List<CredentialVerificationReport> report =
                this.credentialRepository.findCredentialVerificationReport();

        assertThat(report)
                .extracting(CredentialVerificationReport::getTotalVerificationCount)
                .isSortedAccordingTo(Comparator.reverseOrder());

        assertThat(report)
                .filteredOn(item ->
                        item.getCredentialNumber()
                                .equals(credentialWithThreeVerifications.getNumber()))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getTotalVerificationCount()).isEqualTo(3);
                    assertThat(item.getVerifiedVerificationCount()).isEqualTo(2);
                });

        assertThat(report)
                .filteredOn(item ->
                        item.getCredentialNumber()
                                .equals(credentialWithTwoVerifications.getNumber()))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getTotalVerificationCount()).isEqualTo(2);
                    assertThat(item.getVerifiedVerificationCount()).isEqualTo(0);
                });
    }

    private CredentialEntity saveCredential(List<UUID> verificationIds) {
        List<VerificationEntity> verifications = new ArrayList<>(
                verificationIds.stream()
                        .map(this.verificationRepository::getReferenceById)
                        .toList());

        CredentialEntity credential = CredentialEntity.builder()
                .id(UUID.randomUUID())
                .number("REPORT-" + UUID.randomUUID())
                .authority("Test Authority")
                .issueDate(LocalDate.of(2025, 1, 1))
                .expirationDate(LocalDate.of(2030, 1, 1))
                .renewalCount(0)
                .renewable(true)
                .credentialType(CredentialType.CERTIFICATION)
                .verifications(verifications)
                .userId(UUID.randomUUID())
                .build();

        return this.credentialRepository.saveAndFlush(credential);
    }
}