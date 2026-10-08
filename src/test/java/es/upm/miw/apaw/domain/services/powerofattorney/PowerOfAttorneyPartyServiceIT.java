package es.upm.miw.apaw.domain.services.powerofattorney;

import es.upm.miw.apaw.adapters.out.powerofattorney.postgres.PowerOfAttorneyEntity;
import es.upm.miw.apaw.adapters.out.powerofattorney.postgres.PowerOfAttorneyPartyEntity;
import es.upm.miw.apaw.adapters.out.powerofattorney.postgres.PowerOfAttorneyPartyRepository;
import es.upm.miw.apaw.adapters.out.powerofattorney.postgres.PowerOfAttorneyRepository;
import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.powerofattorney.*;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.PowerOfAttorneyPartySeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class PowerOfAttorneyPartyServiceIT {

    @Autowired
    private PowerOfAttorneyPartyService powerOfAttorneyPartyService;

    @Autowired
    private PowerOfAttorneyRepository powerOfAttorneyRepository;

    @Autowired
    private PowerOfAttorneyPartyRepository powerOfAttorneyPartyRepository;

    @MockitoBean
    private UserFinder userFinder;

    @Test
    @Transactional
    void testReadSeeder() {
        when(this.userFinder.read(PARTY_0.getUserSnapshot().getId()))
                .thenReturn(PARTY_0.getUserSnapshot());

        assertThat(this.powerOfAttorneyPartyService.read(ID_0))
                .usingRecursiveComparison()
                .isEqualTo(PARTY_0);
    }

    @Test
    @Transactional
    void testReadNotFound() {
        UUID id = UUID.randomUUID();

        assertThatThrownBy(() -> this.powerOfAttorneyPartyService.read(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    @Transactional
    void testFindAllUserNotFound() {
        when(this.userFinder.findByIds(anySet())).thenReturn(List.of());

        assertThatThrownBy(() -> this.powerOfAttorneyPartyService.findAll())
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(PARTY_0.getUserSnapshot().getId().toString());
    }

    @Test
    @Transactional
    void testFindAllUsesOneUserFinderCall() {
        when(this.userFinder.findByIds(anySet()))
                .thenAnswer(invocation -> ((Set<UUID>) invocation.getArgument(0)).stream()
                        .map(this::seededUserOrIdOnly)
                        .toList());

        List<PowerOfAttorneyParty> parties = this.powerOfAttorneyPartyService.findAll();

        assertThat(parties).extracting(PowerOfAttorneyParty::getId)
                .contains(ID_0, ID_1, ID_2, ID_3, ID_4, ID_5);
        assertThat(parties).filteredOn(party -> party.getId().equals(ID_0))
                .singleElement().extracting(PowerOfAttorneyParty::getUserSnapshot)
                .isEqualTo(PARTY_0.getUserSnapshot());
        verify(this.userFinder, times(1)).findByIds(anySet());
    }

    @Test
    @Transactional
    void testCreate() {
        UserSnapshot user = PARTY_0.getUserSnapshot();
        when(this.userFinder.read(user.getId())).thenReturn(user);

        CreationPowerOfAttorneyParty creation = new CreationPowerOfAttorneyParty(
                30, true, "New Legal S.L.", true, user.getId());

        PowerOfAttorneyParty created = this.powerOfAttorneyPartyService.create(creation);

        assertThat(created.getId()).isNotNull();
        assertThat(created.getAge()).isEqualTo(creation.age());
        assertThat(created.getFullMentalCapacity()).isEqualTo(creation.fullMentalCapacity());
        assertThat(created.getCompanyName()).isEqualTo(creation.companyName());
        assertThat(created.getRepresentationCompany()).isEqualTo(creation.representationCompany());
        assertThat(created.getUserSnapshot().getId()).isEqualTo(user.getId());
        verify(this.userFinder, times(1)).read(user.getId());
    }

    @Test
    @Transactional
    void testUpdateReplacesFields() {
        UserSnapshot user = PARTY_2.getUserSnapshot();
        when(this.userFinder.read(user.getId())).thenReturn(user);

        CreationPowerOfAttorneyParty replacement = new CreationPowerOfAttorneyParty(
                55, false, "Updated Legal S.L.", true, user.getId());

        PowerOfAttorneyParty updated = this.powerOfAttorneyPartyService.update(ID_1, replacement);

        assertThat(updated.getId()).isEqualTo(ID_1);
        assertThat(updated.getAge()).isEqualTo(55);
        assertThat(updated.getFullMentalCapacity()).isFalse();
        assertThat(updated.getCompanyName()).isEqualTo("Updated Legal S.L.");
        assertThat(updated.getRepresentationCompany()).isTrue();
        assertThat(updated.getUserSnapshot().getId()).isEqualTo(user.getId());
    }

    @Test
    @Transactional
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();
        CreationPowerOfAttorneyParty replacement = new CreationPowerOfAttorneyParty(
                55, true, null, false, PARTY_0.getUserSnapshot().getId());

        assertThatThrownBy(() -> this.powerOfAttorneyPartyService.update(id, replacement))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(id.toString());
        verify(this.userFinder, never()).read(PARTY_0.getUserSnapshot().getId());
    }

    @Test
    @Transactional
    void testDelete() {
        UserSnapshot user = PARTY_0.getUserSnapshot();
        when(this.userFinder.read(user.getId())).thenReturn(user);
        UUID id = this.powerOfAttorneyPartyService.create(new CreationPowerOfAttorneyParty(
                44, true, null, false, user.getId())).getId();

        this.powerOfAttorneyPartyService.delete(id);

        assertThatThrownBy(() -> this.powerOfAttorneyPartyService.read(id))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    @Transactional
    void testDeleteReferencedParty() {
        PowerOfAttorneyPartyEntity partyEntity = this.powerOfAttorneyPartyRepository
                .findById(ID_0).orElseThrow();
        PowerOfAttorneyEntity powerOfAttorney = PowerOfAttorneyEntity.builder()
                .id(UUID.randomUUID())
                .protocolNumber("PA-" + UUID.randomUUID())
                .grantDate(LocalDate.now())
                .scope("Legal representation")
                .notaryName("Test Notary")
                .notaryOffice("Test Office")
                .principal(partyEntity)
                .attorney(partyEntity)
                .type(PowerOfAttorneyType.GENERAL)
                .status(PowerOfAttorneyStatus.ACTIVE)
                .build();
        this.powerOfAttorneyRepository.saveAndFlush(powerOfAttorney);

        assertThatThrownBy(() -> this.powerOfAttorneyPartyService.delete(ID_0))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(ID_0.toString());
    }

    @Test
    @Transactional
    void testPatchAgeOnly() {
        when(this.userFinder.findByIds(anySet()))
                .thenAnswer(invocation -> ((Set<UUID>) invocation.getArgument(0)).stream()
                        .map(this::seededUserOrIdOnly)
                        .toList());
        this.powerOfAttorneyPartyService.patch(List.of(
                new PowerOfAttorneyPartyPatch(ID_2, 70, null)));

        assertThat(this.powerOfAttorneyPartyService.findAll())
                .filteredOn(party -> party.getId().equals(ID_2))
                .singleElement()
                .satisfies(party -> {
                    assertThat(party.getAge()).isEqualTo(70);
                    assertThat(party.getFullMentalCapacity()).isFalse();
                });
    }

    @Test
    @Transactional
    void testPatchFullMentalCapacityOnly() {
        this.powerOfAttorneyPartyService.patch(List.of(
                new PowerOfAttorneyPartyPatch(ID_2, null, true)));

        when(this.userFinder.findByIds(anySet()))
                .thenAnswer(invocation -> ((Set<UUID>) invocation.getArgument(0)).stream()
                        .map(this::seededUserOrIdOnly)
                        .toList());
        PowerOfAttorneyParty party = this.powerOfAttorneyPartyService.findAll().stream()
                .filter(value -> value.getId().equals(ID_2))
                .findFirst().orElseThrow();

        assertThat(party.getAge()).isEqualTo(PARTY_2.getAge());
        assertThat(party.getFullMentalCapacity()).isTrue();
    }

    @Test
    @Transactional
    void testPatchBothFields() {
        this.powerOfAttorneyPartyService.patch(List.of(
                new PowerOfAttorneyPartyPatch(ID_2, 70, true)));

        when(this.userFinder.findByIds(anySet()))
                .thenAnswer(invocation -> ((Set<UUID>) invocation.getArgument(0)).stream()
                        .map(this::seededUserOrIdOnly)
                        .toList());
        PowerOfAttorneyParty party = this.powerOfAttorneyPartyService.findAll().stream()
                .filter(value -> value.getId().equals(ID_2))
                .findFirst().orElseThrow();

        assertThat(party.getAge()).isEqualTo(70);
        assertThat(party.getFullMentalCapacity()).isTrue();
    }

    @Test
    @Transactional
    void testPatchRequiresAtLeastOneValue() {
        assertThatThrownBy(() -> this.powerOfAttorneyPartyService.patch(List.of(
                new PowerOfAttorneyPartyPatch(ID_2, null, null))))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("At least one of age or fullMentalCapacity is required");
    }

    @Test
    @Transactional
    void testPatchNotFoundChangesNothing() {
        UUID missingId = UUID.randomUUID();

        assertThatThrownBy(() -> this.powerOfAttorneyPartyService.patch(List.of(
                new PowerOfAttorneyPartyPatch(missingId, 70, null))))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(missingId.toString());
    }

    @Test
    @Transactional
    void testPatchRepeatedIdChangesNothing() {
        assertThatThrownBy(() -> this.powerOfAttorneyPartyService.patch(List.of(
                new PowerOfAttorneyPartyPatch(ID_2, 70, null),
                new PowerOfAttorneyPartyPatch(ID_2, 80, null))))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(ID_2.toString());
    }

    @Test
    @Transactional
    void testFindReport() {
        List<PowerOfAttorneyPartyReport> reports =
                this.powerOfAttorneyPartyService.findReport();

        assertThat(reports).isNotEmpty();
        assertThat(reports)
                .extracting(PowerOfAttorneyPartyReport::getUserId)
                .contains("eeeeffff0000", "eeeeffff0001");
        assertThat(reports)
                .allSatisfy(report -> {
                    assertThat(report.getUserId()).doesNotContain("-");
                    assertThat(report.getTotalPowerOfAttorneysPresent()).isPositive();
                    assertThat(report.getPrincipalCount() + report.getAttorneyCount())
                            .isGreaterThanOrEqualTo(report.getTotalPowerOfAttorneysPresent());
                });
    }


    private UserSnapshot seededUserOrIdOnly(UUID id) {
        return List.of(PARTY_0, PARTY_1, PARTY_2, PARTY_3, PARTY_4, PARTY_5).stream()
                .map(PowerOfAttorneyParty::getUserSnapshot)
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseGet(() -> UserSnapshot.builder().id(id).build());
    }
}
