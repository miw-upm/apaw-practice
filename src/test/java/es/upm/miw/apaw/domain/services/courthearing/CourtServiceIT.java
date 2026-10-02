package es.upm.miw.apaw.domain.services.courthearing;

import es.upm.miw.apaw.adapters.out.courthearing.postgres.CourtEntity;
import es.upm.miw.apaw.adapters.out.courthearing.postgres.CourtHearingEntity;
import es.upm.miw.apaw.adapters.out.courthearing.postgres.CourtHearingRepository;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.courthearing.Court;
import es.upm.miw.apaw.domain.model.courthearing.CourtHearingStatus;
import es.upm.miw.apaw.domain.model.courthearing.CourtHearingType;
import es.upm.miw.apaw.domain.model.courthearing.CourtUpdate;
import es.upm.miw.apaw.domain.model.courthearing.CourtType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.CourtHearingSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class CourtServiceIT {
    @Autowired
    private CourtService courtService;
    @Autowired
    private CourtHearingRepository courtHearingRepository;

    @Test
    void testReadSeeder() {
        assertThat(this.courtService.read(COURT_ID_0)).usingRecursiveComparison().isEqualTo(COURT_0);
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.courtService.read(id))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testFindAllAllowsAdditionalCourts() {
        Court extra = this.createCourt();
        List<Court> courts = this.courtService.findAll();
        assertThat(courts).extracting(Court::getId)
                .contains(COURT_ID_0, COURT_ID_1, COURT_ID_2, COURT_ID_3, COURT_ID_4, extra.getId());
        assertThat(courts).extracting(Court::getId)
                .containsSubsequence(COURT_ID_1, COURT_ID_2, COURT_ID_3, COURT_ID_0);
        assertThat(this.courtService.findAll()).extracting(Court::getId)
                .containsExactlyElementsOf(courts.stream().map(Court::getId).toList());
    }

    @Test
    void testCreate() {
        Court court = this.createCourt();
        assertThat(court.getId()).isNotNull();
        assertThat(this.courtService.read(court.getId())).usingRecursiveComparison().isEqualTo(court);
    }

    @Test
    void testCreateDuplicateName() {
        Court court = this.newCourt();
        court.setName(COURT_0.getName());
        assertThatThrownBy(() -> this.courtService.create(court))
                .isInstanceOf(ConflictException.class).hasMessageContaining(COURT_0.getName());
    }

    @Test
    void testCreateDuplicatePhone() {
        Court court = this.newCourt();
        court.setPhone(COURT_0.getPhone());
        assertThatThrownBy(() -> this.courtService.create(court))
                .isInstanceOf(ConflictException.class).hasMessageContaining(COURT_0.getPhone());
    }

    @Test
    void testCreateWithoutPhoneTwice() {
        Court first = this.newCourt();
        first.setPhone(null);
        Court second = this.newCourt();
        second.setPhone(null);
        assertThat(this.courtService.create(first).getId())
                .isNotEqualTo(this.courtService.create(second).getId());
    }

    @Test
    void testUpdateReplacesMutableFields() {
        Court original = this.createCourt();
        Court replacement = Court.builder().name("Updated " + UUID.randomUUID())
                .address("New address").city("Sevilla").type(CourtType.FAMILY).build();
        this.courtService.update(original.getId(), replacement);
        Court updated = this.courtService.read(original.getId());
        assertThat(updated.getId()).isEqualTo(original.getId());
        assertThat(updated.getName()).isEqualTo(replacement.getName());
        assertThat(updated.getCity()).isEqualTo("Sevilla");
        assertThat(updated.getPhone()).isNull();
        assertThat(updated.getOpeningTime()).isNull();
        assertThat(updated.getType()).isEqualTo(CourtType.FAMILY);
    }

    @Test
    void testUpdateSameUniqueValues() {
        Court court = this.createCourt();
        court.setCity("Bilbao");
        this.courtService.update(court.getId(), court);
        assertThat(this.courtService.read(court.getId()).getCity()).isEqualTo("Bilbao");
    }

    @Test
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.courtService.update(id, COURT_0))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testUpdateDuplicateNameLeavesCourtUnchanged() {
        Court court = this.createCourt();
        Court replacement = Court.builder().name(COURT_0.getName()).address("Address").city("City").build();
        assertThatThrownBy(() -> this.courtService.update(court.getId(), replacement))
                .isInstanceOf(ConflictException.class).hasMessageContaining(COURT_0.getName());
        assertThat(this.courtService.read(court.getId()).getName()).isEqualTo(court.getName());
    }

    @Test
    void testUpdateDuplicatePhoneLeavesCourtUnchanged() {
        Court court = this.createCourt();
        Court replacement = Court.builder().name(court.getName()).address("Address").city("City")
                .phone(COURT_0.getPhone()).build();
        assertThatThrownBy(() -> this.courtService.update(court.getId(), replacement))
                .isInstanceOf(ConflictException.class).hasMessageContaining(COURT_0.getPhone());
        assertThat(this.courtService.read(court.getId()).getPhone()).isEqualTo(court.getPhone());
    }

    @Test
    void testPatchOnlyPresentFields() {
        Court original = this.createCourt();
        this.courtService.patch(original.getId(), new CourtUpdate(null, null, "Barcelona", null, null, null, null));
        Court patched = this.courtService.read(original.getId());
        assertThat(patched.getCity()).isEqualTo("Barcelona");
        assertThat(patched).usingRecursiveComparison().ignoringFields("city").isEqualTo(original);
    }

    @Test
    void testPatchSameName() {
        Court original = this.createCourt();
        this.courtService.patch(original.getId(),
                new CourtUpdate(original.getName(), "New address", null, null, null, null, null));
        assertThat(this.courtService.read(original.getId()).getAddress()).isEqualTo("New address");
    }

    @Test
    void testPatchNotFound() {
        UUID id = UUID.randomUUID();
        CourtUpdate patch = new CourtUpdate(null, null, "Barcelona", null, null, null, null);
        assertThatThrownBy(() -> this.courtService.patch(id, patch))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testPatchDuplicateNameLeavesCourtUnchanged() {
        Court court = this.createCourt();
        CourtUpdate patch = new CourtUpdate(COURT_0.getName(), null, "Barcelona", null, null, null, null);
        assertThatThrownBy(() -> this.courtService.patch(court.getId(), patch))
                .isInstanceOf(ConflictException.class).hasMessageContaining(COURT_0.getName());
        assertThat(this.courtService.read(court.getId())).usingRecursiveComparison().isEqualTo(court);
    }

    @Test
    void testPatchDuplicatePhoneLeavesCourtUnchanged() {
        Court court = this.createCourt();
        CourtUpdate patch = new CourtUpdate(null, null, null, COURT_0.getPhone(), null, null, null);
        assertThatThrownBy(() -> this.courtService.patch(court.getId(), patch))
                .isInstanceOf(ConflictException.class).hasMessageContaining(COURT_0.getPhone());
        assertThat(this.courtService.read(court.getId())).usingRecursiveComparison().isEqualTo(court);
    }

    @Test
    void testDelete() {
        Court court = this.createCourt();
        this.courtService.delete(court.getId());
        assertThatThrownBy(() -> this.courtService.read(court.getId())).isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteMissingCourt() {
        UUID id = UUID.randomUUID();
        this.courtService.delete(id);
        assertThatThrownBy(() -> this.courtService.read(id)).isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteReferencedCourt() {
        Court court = this.createCourt();
        CourtHearingEntity hearing = CourtHearingEntity.builder().id(UUID.randomUUID())
                .date(LocalDateTime.of(2026, 1, 1, 10, 0)).roomNumber("A1")
                .openToPublic(false).remote(false)
                .type(CourtHearingType.TRIAL).status(CourtHearingStatus.SCHEDULED)
                .court(new CourtEntity(court)).attendeeIds(Set.of(UUID.randomUUID())).build();
        this.courtHearingRepository.saveAndFlush(hearing);
        assertThatThrownBy(() -> this.courtService.delete(court.getId()))
                .isInstanceOf(ConflictException.class).hasMessageContaining(court.getId().toString());
        assertThat(this.courtService.read(court.getId()).getId()).isEqualTo(court.getId());
    }

    private Court newCourt() {
        return Court.builder()
                .name("IT court " + UUID.randomUUID())
                .address("IT address")
                .city("Madrid")
                .phone(UUID.randomUUID().toString().substring(0, 8))
                .openingTime(LocalTime.of(9, 0))
                .closingTime(LocalTime.of(17, 0))
                .type(CourtType.CIVIL)
                .build();
    }

    private Court createCourt() {
        return this.courtService.create(this.newCourt());
    }
}