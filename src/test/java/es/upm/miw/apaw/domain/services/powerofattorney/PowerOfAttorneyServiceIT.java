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
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyFindCriteria;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyType;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @MockitoBean
    private UserFinder userFinder;

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
    @Test
    @Transactional
    void testFindCriteriaByFullMentalCapacityTrueRequiresBothParties() {
        PowerOfAttorneyPartyEntity capablePrincipal = this.createParty(35, true);
        PowerOfAttorneyPartyEntity capableAttorney = this.createParty(40, true);
        PowerOfAttorneyPartyEntity incapableParty = this.createParty(45, false);
        PowerOfAttorneyEntity bothCapable = this.createPower(
                "FIND-CAP-TRUE-" + UUID.randomUUID(), capablePrincipal, capableAttorney, PowerOfAttorneyStatus.ACTIVE);
        PowerOfAttorneyEntity oneIncapable = this.createPower(
                "FIND-CAP-MIXED-" + UUID.randomUUID(), capablePrincipal, incapableParty, PowerOfAttorneyStatus.ACTIVE);
        PowerOfAttorneyEntity bothIncapable = this.createPower(
                "FIND-CAP-FALSE-" + UUID.randomUUID(), incapableParty, incapableParty, PowerOfAttorneyStatus.ACTIVE);
        this.stubUsers();

        PowerOfAttorneyFindCriteria criteria = PowerOfAttorneyFindCriteria.builder()
                .status(PowerOfAttorneyStatus.ACTIVE)
                .fullMentalCapacity(true)
                .build();

        List<String> protocolNumbers = this.powerOfAttorneyService.find(criteria).stream()
                .map(PowerOfAttorney::getProtocolNumber)
                .toList();

        assertThat(protocolNumbers)
                .contains(bothCapable.getProtocolNumber())
                .doesNotContain(oneIncapable.getProtocolNumber(), bothIncapable.getProtocolNumber());
    }

    @Test
    @Transactional
    void testFindCriteriaByFullMentalCapacityFalseRequiresAtLeastOneIncapableParty() {
        PowerOfAttorneyPartyEntity capablePrincipal = this.createParty(35, true);
        PowerOfAttorneyPartyEntity capableAttorney = this.createParty(38, true);
        PowerOfAttorneyPartyEntity incapablePrincipal = this.createParty(45, false);
        PowerOfAttorneyPartyEntity incapableAttorney = this.createParty(50, false);
        PowerOfAttorneyEntity bothCapable = this.createPower(
                "FIND-CAP-TRUE-" + UUID.randomUUID(), capablePrincipal, capableAttorney, PowerOfAttorneyStatus.REVOKED);
        PowerOfAttorneyEntity mixed = this.createPower(
                "FIND-CAP-MIXED-" + UUID.randomUUID(), capablePrincipal, incapablePrincipal, PowerOfAttorneyStatus.REVOKED);
        PowerOfAttorneyEntity bothIncapable = this.createPower(
                "FIND-CAP-FALSE-" + UUID.randomUUID(), incapablePrincipal, incapableAttorney, PowerOfAttorneyStatus.REVOKED);
        this.stubUsers();

        PowerOfAttorneyFindCriteria criteria = PowerOfAttorneyFindCriteria.builder()
                .status(PowerOfAttorneyStatus.REVOKED)
                .fullMentalCapacity(false)
                .build();

        List<String> protocolNumbers = this.powerOfAttorneyService.find(criteria).stream()
                .map(PowerOfAttorney::getProtocolNumber).toList();

        assertThat(protocolNumbers)
                .contains(mixed.getProtocolNumber(), bothIncapable.getProtocolNumber())
                .doesNotContain(bothCapable.getProtocolNumber());
    }

    @Test
    @Transactional
    void testFindCriteriaByIdentityMatchesEitherParty() {
        PowerOfAttorneyPartyEntity principal = this.createParty(35, true);
        PowerOfAttorneyPartyEntity attorney = this.createParty(40, true);
        PowerOfAttorneyEntity power = this.createPower(
                "FIND-IDENTITY-" + UUID.randomUUID(), principal, attorney, PowerOfAttorneyStatus.REVOKED);
        this.stubUsers();

        PowerOfAttorneyFindCriteria criteria = PowerOfAttorneyFindCriteria.builder()
                .status(PowerOfAttorneyStatus.REVOKED)
                .identity("ID-" + attorney.getUserId())
                .build();

        List<PowerOfAttorney> result = this.powerOfAttorneyService.find(criteria);

        assertThat(result).extracting(PowerOfAttorney::getProtocolNumber).contains(power.getProtocolNumber());
    }

    @Test
    @Transactional
    void testFindCriteriaCombinesIdentityAndLegalPowerWithAndAndFindsUsersOnce() {
        PowerOfAttorneyPartyEntity legalPrincipal = this.createParty(35, true);
        PowerOfAttorneyPartyEntity legalAttorney = this.createParty(40, true);
        PowerOfAttorneyPartyEntity minor = this.createParty(17, true);
        PowerOfAttorneyEntity legalPower = this.createPower(
                "FIND-LEGAL-" + UUID.randomUUID(), legalPrincipal, legalAttorney, PowerOfAttorneyStatus.ACTIVE);
        PowerOfAttorneyEntity illegalPower = this.createPower(
                "FIND-ILLEGAL-" + UUID.randomUUID(), legalPrincipal, minor, PowerOfAttorneyStatus.ACTIVE);
        this.stubUsers();
        clearInvocations(this.userFinder);

        PowerOfAttorneyFindCriteria criteria = PowerOfAttorneyFindCriteria.builder()
                .status(PowerOfAttorneyStatus.ACTIVE)
                .identity("ID-" + legalPrincipal.getUserId())
                .legalPowerOfAttorney(true)
                .build();

        List<String> protocolNumbers = this.powerOfAttorneyService.find(criteria).stream()
                .map(PowerOfAttorney::getProtocolNumber).toList();

        assertThat(protocolNumbers)
                .contains(legalPower.getProtocolNumber())
                .doesNotContain(illegalPower.getProtocolNumber());
        verify(this.userFinder, times(1)).findByIds(org.mockito.ArgumentMatchers.argThat(userIds ->
                userIds.containsAll(Set.of(legalPrincipal.getUserId(), legalAttorney.getUserId(), minor.getUserId()))));
    }

    @Test
    @Transactional
    void testFindCriteriaWithNullCriteriaReturnsResultsAndEnrichesUsersOnce() {
        PowerOfAttorneyPartyEntity principal = this.createParty(35, true);
        PowerOfAttorneyPartyEntity attorney = this.createParty(40, true);
        PowerOfAttorneyEntity power = this.createPower(
                "FIND-NULL-" + UUID.randomUUID(), principal, attorney, PowerOfAttorneyStatus.ACTIVE);
        this.stubUsers();
        clearInvocations(this.userFinder);

        List<PowerOfAttorney> result = this.powerOfAttorneyService.find(null);

        assertThat(result).extracting(PowerOfAttorney::getProtocolNumber).contains(power.getProtocolNumber());
        assertThat(result).allSatisfy(item -> {
            assertThat(item.getPrincipal().getUserSnapshot().getIdentity()).isNotBlank();
            assertThat(item.getAttorney().getUserSnapshot().getIdentity()).isNotBlank();
        });
        verify(this.userFinder, times(1)).findByIds(anySet());
    }

    @Test
    @Transactional
    void testFindCriteriaByLegalPowerFalseReturnsNonLegalPowers() {
        PowerOfAttorneyPartyEntity adultPrincipal = this.createParty(35, true);
        PowerOfAttorneyPartyEntity adultAttorney = this.createParty(40, true);
        PowerOfAttorneyPartyEntity minor = this.createParty(17, true);
        PowerOfAttorneyEntity legalPower = this.createPower(
                "FIND-LEGAL-TRUE-" + UUID.randomUUID(), adultPrincipal, adultAttorney, PowerOfAttorneyStatus.EXPIRED);
        PowerOfAttorneyEntity nonLegalPower = this.createPower(
                "FIND-LEGAL-FALSE-" + UUID.randomUUID(), adultPrincipal, minor, PowerOfAttorneyStatus.EXPIRED);
        this.stubUsers();

        PowerOfAttorneyFindCriteria criteria = PowerOfAttorneyFindCriteria.builder()
                .status(PowerOfAttorneyStatus.EXPIRED)
                .legalPowerOfAttorney(false)
                .build();

        List<String> protocolNumbers = this.powerOfAttorneyService.find(criteria).stream()
                .map(PowerOfAttorney::getProtocolNumber)
                .toList();

        assertThat(protocolNumbers)
                .contains(nonLegalPower.getProtocolNumber())
                .doesNotContain(legalPower.getProtocolNumber());
    }

    private PowerOfAttorneyPartyEntity createParty(int age, boolean fullMentalCapacity) {
        return this.powerOfAttorneyPartyRepository.saveAndFlush(PowerOfAttorneyPartyEntity.builder()
                .id(UUID.randomUUID())
                .age(age)
                .fullMentalCapacity(fullMentalCapacity)
                .representationCompany(false)
                .userId(UUID.randomUUID())
                .build());
    }

    private PowerOfAttorneyEntity createPower(String protocolNumber, PowerOfAttorneyPartyEntity principal,
                                              PowerOfAttorneyPartyEntity attorney, PowerOfAttorneyStatus status) {
        return this.powerOfAttorneyRepository.saveAndFlush(PowerOfAttorneyEntity.builder()
                .id(UUID.randomUUID())
                .protocolNumber(protocolNumber)
                .grantDate(LocalDate.of(2026, 1, 1))
                .scope("Test scope")
                .notaryName("Test notary")
                .notaryOffice("Test office")
                .principal(principal)
                .attorney(attorney)
                .type(PowerOfAttorneyType.GENERAL)
                .status(status)
                .build());
    }

    private void stubUsers() {
        when(this.userFinder.findByIds(anySet())).thenAnswer(invocation -> {
            Set<UUID> ids = invocation.getArgument(0);
            return ids.stream().map(id -> UserSnapshot.builder()
                    .id(id)
                    .identity("ID-" + id)
                    .firstName("Test user")
                    .build()).collect(Collectors.toList());
        });
    }
}
