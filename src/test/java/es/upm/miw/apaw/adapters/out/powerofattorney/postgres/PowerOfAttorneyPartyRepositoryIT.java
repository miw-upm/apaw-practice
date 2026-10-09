package es.upm.miw.apaw.adapters.out.powerofattorney.postgres;

import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyPartyReport;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyStatus;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class PowerOfAttorneyPartyRepositoryIT {

    @Autowired
    private PowerOfAttorneyPartyRepository powerOfAttorneyPartyRepository;

    @Autowired
    private PowerOfAttorneyRepository powerOfAttorneyRepository;

    @Test
    void testFindReport() {
        UUID principalUserId = UUID.randomUUID();
        UUID attorneyUserId = UUID.randomUUID();

        PowerOfAttorneyPartyEntity principal = this.saveParty(principalUserId);
        PowerOfAttorneyPartyEntity attorney = this.saveParty(attorneyUserId);

        this.savePowerOfAttorney(principal, attorney);
        this.savePowerOfAttorney(principal, attorney);
        this.savePowerOfAttorney(attorney, principal);

        List<PowerOfAttorneyPartyReport> report =
                this.powerOfAttorneyPartyRepository.findReport();

        assertThat(report)
                .filteredOn(item -> item.getUserId().equals(this.shortId(principalUserId)))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getTotalPowerOfAttorneysPresent()).isEqualTo(3);
                    assertThat(item.getPrincipalCount()).isEqualTo(2);
                    assertThat(item.getAttorneyCount()).isEqualTo(1);
                });

        assertThat(report)
                .filteredOn(item -> item.getUserId().equals(this.shortId(attorneyUserId)))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getTotalPowerOfAttorneysPresent()).isEqualTo(3);
                    assertThat(item.getPrincipalCount()).isEqualTo(1);
                    assertThat(item.getAttorneyCount()).isEqualTo(2);
                });
    }

    private PowerOfAttorneyPartyEntity saveParty(UUID userId) {
        return this.powerOfAttorneyPartyRepository.saveAndFlush(
                PowerOfAttorneyPartyEntity.builder()
                        .id(UUID.randomUUID())
                        .age(40)
                        .fullMentalCapacity(true)
                        .representationCompany(false)
                        .userId(userId)
                        .build());
    }

    private void savePowerOfAttorney(
            PowerOfAttorneyPartyEntity principal,
            PowerOfAttorneyPartyEntity attorney) {
        this.powerOfAttorneyRepository.saveAndFlush(
                PowerOfAttorneyEntity.builder()
                        .id(UUID.randomUUID())
                        .protocolNumber("IT-" + UUID.randomUUID())
                        .grantDate(LocalDate.of(2025, 1, 1))
                        .scope("Integration test scope")
                        .notaryName("Integration test notary")
                        .notaryOffice("Integration test office")
                        .principal(principal)
                        .attorney(attorney)
                        .type(PowerOfAttorneyType.GENERAL)
                        .status(PowerOfAttorneyStatus.ACTIVE)
                        .build());
    }

    private String shortId(UUID userId) {
        String value = userId.toString();
        return value.substring(value.lastIndexOf('-') + 1);
    }
}


