package es.upm.miw.apaw.domain.model.deadlinecalculator;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

    private UserSnapshot userSnapshot;

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
}
