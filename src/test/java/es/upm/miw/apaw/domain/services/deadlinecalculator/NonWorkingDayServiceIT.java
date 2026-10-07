package es.upm.miw.apaw.domain.services.deadlinecalculator;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.deadlinecalculator.NonWorkingDay;
import es.upm.miw.apaw.domain.model.deadlinecalculator.NonWorkingDayRecurringUpdate;
import es.upm.miw.apaw.domain.model.deadlinecalculator.ScopeLevel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.DeadlineCalculatorSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class NonWorkingDayServiceIT {
    private static final String MADRID = "Madrid";

    @Autowired
    private NonWorkingDayService nonWorkingDayService;

    private NonWorkingDay.NonWorkingDayBuilder national(int month, int day) {
        return NonWorkingDay.builder()
                .date(LocalDate.of(2030, month, day))
                .description("Festivo de prueba " + month + "-" + day)
                .scopeLevel(ScopeLevel.NATIONAL);
    }

    private NonWorkingDay duplicateOfSeeder(String region) {
        return NonWorkingDay.builder()
                .date(NON_WORKING_DAY_0.getDate())
                .description("Duplicado")
                .scopeLevel(ScopeLevel.NATIONAL)
                .region(region)
                .build();
    }

    private void assertCreateBadRequest(NonWorkingDay nonWorkingDay) {
        assertThatThrownBy(() -> this.nonWorkingDayService.create(nonWorkingDay))
                .isInstanceOf(BadRequestException.class);
    }

    private void assertCreateConflict(NonWorkingDay nonWorkingDay) {
        assertThatThrownBy(() -> this.nonWorkingDayService.create(nonWorkingDay))
                .isInstanceOf(ConflictException.class);
    }

    // ---------- create ----------

    @Test
    @Transactional
    void testCreateWithoutScopeLevel() {
        this.assertCreateBadRequest(this.national(2, 1).scopeLevel(null).build());
    }

    @Test
    @Transactional
    void testCreateNationalWithRegion() {
        this.assertCreateBadRequest(this.national(2, 2).region(MADRID).build());
    }

    @Test
    @Transactional
    void testCreateNationalWithCity() {
        this.assertCreateBadRequest(this.national(2, 3).city(MADRID).build());
    }

    @Test
    @Transactional
    void testCreateRegionalWithoutRegion() {
        this.assertCreateBadRequest(this.national(2, 4).scopeLevel(ScopeLevel.REGIONAL).build());
    }

    @Test
    @Transactional
    void testCreateRegionalWithCity() {
        this.assertCreateBadRequest(this.national(2, 5)
                .scopeLevel(ScopeLevel.REGIONAL).region(MADRID).city(MADRID).build());
    }

    @Test
    @Transactional
    void testCreateLocalWithoutRegion() {
        this.assertCreateBadRequest(this.national(2, 6).scopeLevel(ScopeLevel.LOCAL).city(MADRID).build());
    }

    @Test
    @Transactional
    void testCreateLocalWithoutCity() {
        this.assertCreateBadRequest(this.national(2, 7).scopeLevel(ScopeLevel.LOCAL).region(MADRID).build());
    }

    @Test
    @Transactional
    void testCreateLocalWithBlankCity() {
        this.assertCreateBadRequest(this.national(2, 8)
                .scopeLevel(ScopeLevel.LOCAL).region(MADRID).city("   ").build());
    }

    @Test
    @Transactional
    void testCreateDuplicate() {
        this.assertCreateConflict(this.duplicateOfSeeder(null));
    }

    @Test
    @Transactional
    void testCreateDuplicateWithEmptyRegion() {
        this.assertCreateConflict(this.duplicateOfSeeder(""));
    }

    @Test
    @Transactional
    void testCreateDuplicateWithBlankRegion() {
        this.assertCreateConflict(this.duplicateOfSeeder("   "));
    }

    @Test
    @Transactional
    void testCreateWithoutRecurringDefaultsToFalse() {
        assertThat(this.nonWorkingDayService.create(this.national(2, 9).build()).getRecurring()).isFalse();
    }

    @Test
    @Transactional
    void testCreateKeepsRecurringTrue() {
        assertThat(this.nonWorkingDayService.create(this.national(2, 10).recurring(true).build())
                .getRecurring()).isTrue();
    }

    @Test
    @Transactional
    void testCreateNationalReturnsNullRegionAndCity() {
        NonWorkingDay created = this.nonWorkingDayService.create(this.national(2, 11).build());
        assertThat(created.getRegion()).isNull();
        assertThat(created.getCity()).isNull();
    }

    @Test
    @Transactional
    void testCreateLocalOnTheSameDayAsNational() {
        NonWorkingDay national = this.nonWorkingDayService.create(this.national(2, 12).build());
        NonWorkingDay local = this.nonWorkingDayService.create(this.national(2, 12)
                .scopeLevel(ScopeLevel.LOCAL).region(MADRID).city("Leganés").build());
        assertThat(local.getId()).isNotEqualTo(national.getId());
        assertThat(local).usingRecursiveComparison().ignoringFields("id")
                .isEqualTo(this.national(2, 12).scopeLevel(ScopeLevel.LOCAL)
                        .region(MADRID).city("Leganés").recurring(false).build());
    }

    // ---------- read ----------

    @Test
    @Transactional
    void testRead() {
        NonWorkingDay read = this.nonWorkingDayService.read(ID_5);
        assertThat(read).usingRecursiveComparison().isEqualTo(NON_WORKING_DAY_5);
    }

    @Test
    @Transactional
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.nonWorkingDayService.read(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    // ---------- findAll ----------

    @Test
    @Transactional
    void testFindAllContainsSeeder() {
        assertThat(this.nonWorkingDayService.findAll())
                .extracting(NonWorkingDay::getId)
                .contains(ID_0, ID_5, ID_6, ID_10);
    }

    @Test
    @Transactional
    void testFindAllOrdersSameDateAndDescriptionById() {
        List<UUID> ids = this.nonWorkingDayService.findAll().stream()
                .map(NonWorkingDay::getId)
                .filter(id -> id.equals(ID_5) || id.equals(ID_6))
                .toList();
        assertThat(ids).containsExactly(ID_5, ID_6);
    }

    // ---------- update ----------

    @Test
    @Transactional
    void testUpdateWithoutChanges() {
        NonWorkingDay created = this.nonWorkingDayService.create(this.national(3, 1).build());
        NonWorkingDay updated = this.nonWorkingDayService.update(created.getId(), created);
        assertThat(updated).usingRecursiveComparison().isEqualTo(created);
    }

    @Test
    @Transactional
    void testUpdateOnlyDescription() {
        NonWorkingDay created = this.nonWorkingDayService.create(this.national(3, 2).build());
        created.setDescription("Nombre corregido");
        assertThat(this.nonWorkingDayService.update(created.getId(), created).getDescription())
                .isEqualTo("Nombre corregido");
    }

    @Test
    @Transactional
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();
        NonWorkingDay nonWorkingDay = this.national(3, 3).build();
        assertThatThrownBy(() -> this.nonWorkingDayService.update(id, nonWorkingDay))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    @Transactional
    void testUpdateInconsistentScope() {
        NonWorkingDay created = this.nonWorkingDayService.create(this.national(3, 4).build());
        created.setRegion(MADRID);
        UUID id = created.getId();
        assertThatThrownBy(() -> this.nonWorkingDayService.update(id, created))
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    @Transactional
    void testUpdateToAnExistingKeyConflict() {
        NonWorkingDay created = this.nonWorkingDayService.create(this.national(3, 5).build());
        created.setDate(NON_WORKING_DAY_0.getDate());
        UUID id = created.getId();
        assertThatThrownBy(() -> this.nonWorkingDayService.update(id, created))
                .isInstanceOf(ConflictException.class);
    }

    @Test
    @Transactional
    void testUpdateToAnExistingKeyWithEmptyRegionConflict() {
        NonWorkingDay created = this.nonWorkingDayService.create(this.national(3, 6).build());
        created.setDate(NON_WORKING_DAY_0.getDate());
        created.setRegion("");
        UUID id = created.getId();
        assertThatThrownBy(() -> this.nonWorkingDayService.update(id, created))
                .isInstanceOf(ConflictException.class);
    }

    @Test
    @Transactional
    void testUpdateWithoutRecurringDefaultsToFalse() {
        NonWorkingDay created = this.nonWorkingDayService.create(this.national(3, 7).recurring(true).build());
        created.setRecurring(null);
        assertThat(this.nonWorkingDayService.update(created.getId(), created).getRecurring()).isFalse();
    }

    @Test
    @Transactional
    void testUpdateFromLocalToNationalClearsRegionAndCity() {
        NonWorkingDay created = this.nonWorkingDayService.create(this.national(3, 8)
                .scopeLevel(ScopeLevel.LOCAL).region(MADRID).city("Pinto").build());
        created.setScopeLevel(ScopeLevel.NATIONAL);
        created.setRegion(null);
        created.setCity(null);
        NonWorkingDay updated = this.nonWorkingDayService.update(created.getId(), created);
        assertThat(updated.getRegion()).isNull();
        assertThat(updated.getCity()).isNull();
    }

    @Test
    @Transactional
    void testUpdateKeepsTheIdentityOfThePath() {
        NonWorkingDay created = this.nonWorkingDayService.create(this.national(3, 9).build());
        NonWorkingDay body = this.national(3, 10).id(UUID.randomUUID()).build();
        assertThat(this.nonWorkingDayService.update(created.getId(), body).getId())
                .isEqualTo(created.getId());
    }

    @Test
    @Transactional
    void testUpdateReferencedDateConflict() {
        NonWorkingDay referenced = this.nonWorkingDayService.read(ID_10);
        referenced.setDate(LocalDate.of(2030, 3, 11));
        assertThatThrownBy(() -> this.nonWorkingDayService.update(ID_10, referenced))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("used by a deadline");
    }

    @Test
    @Transactional
    void testUpdateReferencedOnlyDescription() {
        NonWorkingDay referenced = this.nonWorkingDayService.read(ID_10);
        referenced.setDescription("Festividad local corregida");
        assertThat(this.nonWorkingDayService.update(ID_10, referenced).getDescription())
                .isEqualTo("Festividad local corregida");
    }

    // ---------- updateRecurrences ----------

    @Test
    @Transactional
    void testUpdateRecurrences() {
        NonWorkingDay first = this.nonWorkingDayService.create(this.national(5, 1).build());
        NonWorkingDay second = this.nonWorkingDayService.create(this.national(5, 2).build());
        this.nonWorkingDayService.updateRecurrences(List.of(
                new NonWorkingDayRecurringUpdate(first.getId(), true),
                new NonWorkingDayRecurringUpdate(second.getId(), true)));
        assertThat(this.nonWorkingDayService.read(first.getId()).getRecurring()).isTrue();
        assertThat(this.nonWorkingDayService.read(second.getId()).getRecurring()).isTrue();
    }

    @Test
    @Transactional
    void testUpdateRecurrencesWithRepeatedId() {
        NonWorkingDay created = this.nonWorkingDayService.create(this.national(5, 3).build());
        List<NonWorkingDayRecurringUpdate> updates = List.of(
                new NonWorkingDayRecurringUpdate(created.getId(), true),
                new NonWorkingDayRecurringUpdate(created.getId(), false));
        assertThatThrownBy(() -> this.nonWorkingDayService.updateRecurrences(updates))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(created.getId().toString());
    }

    @Test
    @Transactional
    void testUpdateRecurrencesWithUnknownIdFailsBeforeAnyWrite() {
        NonWorkingDay created = this.nonWorkingDayService.create(this.national(5, 4).build());
        List<NonWorkingDayRecurringUpdate> updates = List.of(
                new NonWorkingDayRecurringUpdate(created.getId(), true),
                new NonWorkingDayRecurringUpdate(UUID.randomUUID(), true));
        assertThatThrownBy(() -> this.nonWorkingDayService.updateRecurrences(updates))
                .isInstanceOf(NotFoundException.class);
        assertThat(this.nonWorkingDayService.read(created.getId()).getRecurring()).isFalse();
    }

    // ---------- delete ----------

    @Test
    @Transactional
    void testDelete() {
        NonWorkingDay created = this.nonWorkingDayService.create(this.national(7, 1).build());
        UUID id = created.getId();
        this.nonWorkingDayService.delete(id);
        assertThatThrownBy(() -> this.nonWorkingDayService.read(id))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    @Transactional
    void testDeleteIsIdempotent() {
        NonWorkingDay created = this.nonWorkingDayService.create(this.national(7, 2).build());
        this.nonWorkingDayService.delete(created.getId());
        this.nonWorkingDayService.delete(created.getId());
    }

    @Test
    @Transactional
    void testDeleteReferencedConflict() {
        assertThatThrownBy(() -> this.nonWorkingDayService.delete(ID_10))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("referenced by a deadline");
    }
}
