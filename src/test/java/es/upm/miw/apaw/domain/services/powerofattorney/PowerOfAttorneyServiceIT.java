package es.upm.miw.apaw.domain.services.powerofattorney;

import es.upm.miw.apaw.adapters.out.powerofattorney.postgres.PowerOfAttorneyEntity;
import es.upm.miw.apaw.adapters.out.powerofattorney.postgres.PowerOfAttorneyPartyEntity;
import es.upm.miw.apaw.adapters.out.powerofattorney.postgres.PowerOfAttorneyPartyRepository;
import es.upm.miw.apaw.adapters.out.powerofattorney.postgres.PowerOfAttorneyRepository;
import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.powerofattorney.CreationPowerOfAttorney;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorney;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyStatus;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.PowerOfAttorneyPartySeederForDev.ID_0;
import static es.upm.miw.apaw.config.seeders.PowerOfAttorneyPartySeederForDev.ID_1;
import static es.upm.miw.apaw.config.seeders.PowerOfAttorneyPartySeederForDev.ID_2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class PowerOfAttorneyServiceIT {

    @Autowired
    private PowerOfAttorneyService powerOfAttorneyService;

    @Autowired
    private PowerOfAttorneyRepository powerOfAttorneyRepository;

    @Autowired
    private PowerOfAttorneyPartyRepository powerOfAttorneyPartyRepository;

    @Test
    @Transactional
    void testCreate() {
        CreationPowerOfAttorney creation = CreationPowerOfAttorney.builder()
                .protocolNumber("NEW-" + UUID.randomUUID())
                .grantDate(LocalDate.of(2026, 10, 1))
                .expirationDate(LocalDate.of(2027, 10, 1))
                .scope("General legal representation")
                .limitations(null)
                .notaryName("Notary Name")
                .notaryOffice("Notary Office")
                .notes(null)
                .principalId(ID_0)
                .attorneyId(ID_2)
                .build();

        PowerOfAttorney created = this.powerOfAttorneyService.create(creation);

        assertThat(created.getId()).isNotNull();
        assertThat(created.getProtocolNumber()).isEqualTo(creation.getProtocolNumber());
        assertThat(created.getGrantDate()).isEqualTo(creation.getGrantDate());
        assertThat(created.getExpirationDate()).isEqualTo(creation.getExpirationDate());
        assertThat(created.getScope()).isEqualTo(creation.getScope());
        assertThat(created.getLimitations()).isEqualTo(creation.getLimitations());
        assertThat(created.getNotaryName()).isEqualTo(creation.getNotaryName());
        assertThat(created.getNotaryOffice()).isEqualTo(creation.getNotaryOffice());
        assertThat(created.getNotes()).isEqualTo(creation.getNotes());
        assertThat(created.getPrincipal().getId()).isEqualTo(ID_0);
        assertThat(created.getAttorney().getId()).isEqualTo(ID_2);
        assertThat(created.getType()).isEqualTo(PowerOfAttorneyType.GENERAL);
        assertThat(created.getStatus()).isEqualTo(PowerOfAttorneyStatus.ACTIVE);
    }

    @Test
    @Transactional
    void testCreateDuplicatedProtocolNumber() {
        String protocolNumber = "DUPLICATED-" + UUID.randomUUID();

        PowerOfAttorneyPartyEntity principal = this.powerOfAttorneyPartyRepository
                .findById(ID_0)
                .orElseThrow();
        PowerOfAttorneyPartyEntity attorney = this.powerOfAttorneyPartyRepository
                .findById(ID_2)
                .orElseThrow();

        this.powerOfAttorneyRepository.saveAndFlush(PowerOfAttorneyEntity.builder()
                .id(UUID.randomUUID())
                .protocolNumber(protocolNumber)
                .grantDate(LocalDate.of(2026, 10, 1))
                .expirationDate(LocalDate.of(2027, 10, 1))
                .scope("General legal representation")
                .notaryName("Notary Name")
                .notaryOffice("Notary Office")
                .principal(principal)
                .attorney(attorney)
                .type(PowerOfAttorneyType.GENERAL)
                .status(PowerOfAttorneyStatus.ACTIVE)
                .build());

        CreationPowerOfAttorney creation = CreationPowerOfAttorney.builder()
                .protocolNumber(protocolNumber)
                .grantDate(LocalDate.of(2026, 10, 1))
                .expirationDate(LocalDate.of(2027, 10, 1))
                .scope("General legal representation")
                .notaryName("Notary Name")
                .notaryOffice("Notary Office")
                .principalId(ID_0)
                .attorneyId(ID_2)
                .build();

        assertThatThrownBy(() -> this.powerOfAttorneyService.create(creation))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(protocolNumber);
    }

    @Test
    @Transactional
    void testCreateGrantDateAfterExpirationDate() {
        CreationPowerOfAttorney creation = CreationPowerOfAttorney.builder()
                .protocolNumber("NEW-" + UUID.randomUUID())
                .grantDate(LocalDate.of(2027, 10, 1))
                .expirationDate(LocalDate.of(2026, 10, 1))
                .scope("General legal representation")
                .notaryName("Notary Name")
                .notaryOffice("Notary Office")
                .principalId(ID_0)
                .attorneyId(ID_2)
                .build();

        assertThatThrownBy(() -> this.powerOfAttorneyService.create(creation))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("grand date cannot be later than expiration date");
    }

    @Test
    @Transactional
    void testCreateWithSamePrincipalAndAttorney() {
        CreationPowerOfAttorney creation = CreationPowerOfAttorney.builder()
                .protocolNumber("NEW-" + UUID.randomUUID())
                .grantDate(LocalDate.of(2026, 10, 1))
                .expirationDate(LocalDate.of(2027, 10, 1))
                .scope("General legal representation")
                .notaryName("Notary Name")
                .notaryOffice("Notary Office")
                .principalId(ID_0)
                .attorneyId(ID_1)
                .build();

        assertThatThrownBy(() -> this.powerOfAttorneyService.create(creation))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Principal and attorney must be different users");
    }
}
