package es.upm.miw.apaw.domain.model.deadlinecalculator;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Deadline {

    @EqualsAndHashCode.Include
    private UUID id;

    private String title;

    private String courtFileNumber;

    private LocalDate notificationDate;

    private Integer days;

    private DayCountType dayCountType;

    private String region;

    private String city;

    private DeadlineStatus status;

    private LocalDateTime createdAt;

    private LocalDate dueDate;

    private List<NonWorkingDay> nonWorkingDays;

    private UserSnapshot lawyer;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        if (this.dayCountType == null) {
            this.dayCountType = DayCountType.WORKING;
        }
        if (this.status == null) {
            this.status = DeadlineStatus.PENDING;
        }
    }

    public boolean hasWorkingDayCount() {
        return DayCountType.WORKING == this.dayCountType;
    }

    public Deadline ofSummary() {
        return Deadline.builder()
                .id(this.id)
                .title(this.title)
                .courtFileNumber(this.courtFileNumber)
                .notificationDate(this.notificationDate)
                .days(this.days)
                .dayCountType(this.dayCountType)
                .region(this.region)
                .city(this.city)
                .status(this.status)
                .createdAt(this.createdAt)
                .dueDate(this.dueDate)
                .lawyer(UserSnapshot.builder()
                        .id(this.lawyer.getId())
                        .mobile(this.lawyer.getMobile())
                        .firstName(this.lawyer.getFirstName())
                        .build())
                .build();
    }

    public void doCalculate() {
        if (!this.hasWorkingDayCount()) {
            this.dueDate = this.notificationDate.plusDays(this.days);
            this.nonWorkingDays = List.of();
            return;
        }
        Set<LocalDate> nonWorkingDates = this.nonWorkingDays.stream()
                .map(NonWorkingDay::getDate)
                .collect(Collectors.toSet());
        LocalDate current = this.notificationDate;
        int counted = 0;
        while (counted < this.days) {
            current = current.plusDays(1);
            if (this.isWorkingDay(current, nonWorkingDates)) {
                counted++;
            }
        }
        this.dueDate = current;
        this.nonWorkingDays = this.nonWorkingDays.stream()
                .filter(this::isWithinPeriod)
                .toList();
    }

    private boolean isWorkingDay(LocalDate date, Set<LocalDate> nonWorkingDates) {
        return date.getDayOfWeek() != DayOfWeek.SATURDAY
                && date.getDayOfWeek() != DayOfWeek.SUNDAY
                && date.getMonth() != Month.AUGUST
                && !this.isChristmasPeriod(date)
                && !nonWorkingDates.contains(date);
    }

    private boolean isChristmasPeriod(LocalDate date) {
        return (date.getMonth() == Month.DECEMBER && date.getDayOfMonth() >= 24)
                || (date.getMonth() == Month.JANUARY && date.getDayOfMonth() <= 6);
    }

    private boolean isWithinPeriod(NonWorkingDay nonWorkingDay) {
        return nonWorkingDay.getDate().isAfter(this.notificationDate)
                && !nonWorkingDay.getDate().isAfter(this.dueDate);
    }
}
