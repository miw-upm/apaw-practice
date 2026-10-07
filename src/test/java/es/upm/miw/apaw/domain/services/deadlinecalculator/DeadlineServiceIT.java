package es.upm.miw.apaw.domain.services.deadlinecalculator;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.deadlinecalculator.CreationDeadline;
import es.upm.miw.apaw.domain.model.deadlinecalculator.DayCountType;
import es.upm.miw.apaw.domain.model.deadlinecalculator.Deadline;
import es.upm.miw.apaw.domain.model.deadlinecalculator.DeadlineStatus;
import es.upm.miw.apaw.domain.model.deadlinecalculator.NonWorkingDay;
import es.upm.miw.apaw.domain.model.deadlinecalculator.ScopeLevel;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class DeadlineServiceIT {
    private static final UUID USER_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000");
    private static final UserSnapshot USER = UserSnapshot.builder()
            .id(USER_ID)
            .mobile("600000100")
            .firstName("Lawyer")
            .build();

    @Autowired
    private DeadlineService deadlineService;
    @Autowired
    private NonWorkingDayService nonWorkingDayService;
    @MockitoBean
    private UserFinder userFinder;

    @BeforeEach
    void setUp() {
        when(this.userFinder.read(USER_ID)).thenReturn(USER);
    }

    private CreationDeadline.CreationDeadlineBuilder creation(String territory, LocalDate notificationDate, int days) {
        return CreationDeadline.builder()
                .title("Plazo " + territory + " " + UUID.randomUUID())
                .notificationDate(notificationDate)
                .days(days)
                .region("Region " + territory)
                .city("City " + territory)
                .userId(USER_ID);
    }

    private void localHoliday(String territory, LocalDate date) {
        this.nonWorkingDayService.create(NonWorkingDay.builder()
                .date(date)
                .description("Festivo local " + territory)
                .scopeLevel(ScopeLevel.LOCAL)
                .region("Region " + territory)
                .city("City " + territory)
                .build());
    }

    private void regionalHoliday(String territory, LocalDate date) {
        this.nonWorkingDayService.create(NonWorkingDay.builder()
                .date(date)
                .description("Festivo autonomico " + territory)
                .scopeLevel(ScopeLevel.REGIONAL)
                .region("Region " + territory)
                .build());
    }

    // ---------- reglas de negocio ----------

    @Test
    @Transactional
    void testCreate() {
        this.localHoliday("A", LocalDate.of(2035, 1, 15));
        Deadline created = this.deadlineService.create(
                this.creation("A", LocalDate.of(2035, 1, 11), 5).courtFileNumber("123/2035").build());
        assertThat(created.getId()).isNotNull();
        assertThat(created.getCreatedAt()).isNotNull();
        assertThat(created.getStatus()).isEqualTo(DeadlineStatus.PENDING);
        assertThat(created.getDayCountType()).isEqualTo(DayCountType.WORKING);
        assertThat(created.getDueDate()).isEqualTo(LocalDate.of(2035, 1, 19));
        assertThat(created.getUserSnapshot()).usingRecursiveComparison().isEqualTo(USER);
        assertThat(created.getNonWorkingDays()).extracting(NonWorkingDay::getDate)
                .containsExactly(LocalDate.of(2035, 1, 15));
    }

    @Test
    @Transactional
    void testCreateWithADuplicatedTitle() {
        CreationDeadline creation = this.creation("B", LocalDate.of(2035, 1, 11), 2).build();
        this.deadlineService.create(creation);
        assertThatThrownBy(() -> this.deadlineService.create(creation))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(creation.getTitle());
    }

    @Test
    @Transactional
    void testCreateWithAnUnknownUser() {
        UUID unknownUserId = UUID.randomUUID();
        when(this.userFinder.read(unknownUserId)).thenThrow(new NotFoundException("User id not found"));
        CreationDeadline creation = this.creation("C", LocalDate.of(2035, 1, 11), 2)
                .userId(unknownUserId).build();
        assertThatThrownBy(() -> this.deadlineService.create(creation))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    @Transactional
    void testCreateWithoutDayCountTypeDefaultsToWorking() {
        Deadline created = this.deadlineService.create(
                this.creation("D", LocalDate.of(2035, 1, 11), 2).build());
        assertThat(created.getDayCountType()).isEqualTo(DayCountType.WORKING);
    }

    @Test
    @Transactional
    void testCreateReadsTheUserOnlyOnce() {
        this.deadlineService.create(this.creation("E", LocalDate.of(2035, 1, 11), 2).build());
        verify(this.userFinder, times(1)).read(USER_ID);
    }

    // ---------- cómputo del vencimiento ----------

    @Test
    @Transactional
    void testCalculateWithASingleDay() {
        Deadline created = this.deadlineService.create(
                this.creation("F", LocalDate.of(2035, 1, 11), 1).build());
        assertThat(created.getDueDate()).isEqualTo(LocalDate.of(2035, 1, 12));
        assertThat(created.getNonWorkingDays()).isEmpty();
    }

    @Test
    @Transactional
    void testCalculateNotifiedOnFriday() {
        Deadline created = this.deadlineService.create(
                this.creation("G", LocalDate.of(2035, 1, 12), 3).build());
        assertThat(created.getDueDate()).isEqualTo(LocalDate.of(2035, 1, 17));
    }

    @Test
    @Transactional
    void testCalculateNotifiedOnSaturday() {
        Deadline created = this.deadlineService.create(
                this.creation("H", LocalDate.of(2035, 1, 13), 3).build());
        assertThat(created.getDueDate()).isEqualTo(LocalDate.of(2035, 1, 17));
    }

    @Test
    @Transactional
    void testCalculateWithAHolidayOnTheFollowingDay() {
        this.localHoliday("I", LocalDate.of(2035, 5, 11));
        Deadline created = this.deadlineService.create(
                this.creation("I", LocalDate.of(2035, 5, 10), 3).build());
        assertThat(created.getDueDate()).isEqualTo(LocalDate.of(2035, 5, 16));
        assertThat(created.getNonWorkingDays()).hasSize(1);
    }

    @Test
    @Transactional
    void testCalculateAcrossAugust() {
        Deadline created = this.deadlineService.create(
                this.creation("J", LocalDate.of(2035, 7, 30), 3).build());
        assertThat(created.getDueDate()).isEqualTo(LocalDate.of(2035, 9, 4));
    }

    @Test
    @Transactional
    void testCalculateAcrossTheChristmasPeriod() {
        Deadline created = this.deadlineService.create(
                this.creation("K", LocalDate.of(2035, 12, 20), 3).build());
        assertThat(created.getDueDate()).isEqualTo(LocalDate.of(2036, 1, 8));
    }

    @Test
    @Transactional
    void testCalculateWithAHolidayOnSaturdayKeepsItInTheRelation() {
        this.localHoliday("L", LocalDate.of(2035, 5, 12));
        Deadline created = this.deadlineService.create(
                this.creation("L", LocalDate.of(2035, 5, 9), 3).build());
        assertThat(created.getDueDate()).isEqualTo(LocalDate.of(2035, 5, 14));
        assertThat(created.getNonWorkingDays()).extracting(NonWorkingDay::getDate)
                .containsExactly(LocalDate.of(2035, 5, 12));
    }

    @Test
    @Transactional
    void testCalculateWithTwoHolidaysOnTheSameDay() {
        this.localHoliday("M", LocalDate.of(2035, 5, 11));
        this.regionalHoliday("M", LocalDate.of(2035, 5, 11));
        Deadline created = this.deadlineService.create(
                this.creation("M", LocalDate.of(2035, 5, 9), 3).build());
        assertThat(created.getDueDate()).isEqualTo(LocalDate.of(2035, 5, 15));
        assertThat(created.getNonWorkingDays()).hasSize(2)
                .extracting(NonWorkingDay::getDate)
                .containsOnly(LocalDate.of(2035, 5, 11));
    }

    @Test
    @Transactional
    void testCalculateWithoutApplicableHolidays() {
        Deadline created = this.deadlineService.create(
                this.creation("N", LocalDate.of(2035, 1, 11), 5).build());
        assertThat(created.getDueDate()).isEqualTo(LocalDate.of(2035, 1, 18));
        assertThat(created.getNonWorkingDays()).isEmpty();
    }

    @Test
    @Transactional
    void testCalculateWithCalendarDays() {
        this.localHoliday("O", LocalDate.of(2035, 1, 15));
        Deadline created = this.deadlineService.create(
                this.creation("O", LocalDate.of(2035, 1, 11), 5)
                        .dayCountType(DayCountType.CALENDAR).build());
        assertThat(created.getDueDate()).isEqualTo(LocalDate.of(2035, 1, 16));
        assertThat(created.getNonWorkingDays()).isEmpty();
    }

    @Test
    @Transactional
    void testCalculateWithCalendarDaysAcrossTheChristmasPeriod() {
        this.localHoliday("P", LocalDate.of(2035, 12, 25));
        Deadline created = this.deadlineService.create(
                this.creation("P", LocalDate.of(2035, 12, 20), 21)
                        .dayCountType(DayCountType.CALENDAR).build());
        assertThat(created.getDueDate()).isEqualTo(LocalDate.of(2036, 1, 10));
        assertThat(created.getNonWorkingDays()).isEmpty();
    }

    @Test
    @Transactional
    void testCalculateWithCalendarDaysEndingOnSunday() {
        Deadline created = this.deadlineService.create(
                this.creation("Q", LocalDate.of(2035, 1, 9), 5)
                        .dayCountType(DayCountType.CALENDAR).build());
        assertThat(created.getDueDate()).isEqualTo(LocalDate.of(2035, 1, 14));
    }

    @Test
    @Transactional
    void testCalculateKeepsOutTheHolidaysBeyondThePeriod() {
        this.localHoliday("R", LocalDate.of(2035, 3, 19));
        Deadline created = this.deadlineService.create(
                this.creation("R", LocalDate.of(2035, 1, 11), 2).build());
        assertThat(created.getDueDate()).isEqualTo(LocalDate.of(2035, 1, 15));
        assertThat(created.getNonWorkingDays()).isEmpty();
    }
}
