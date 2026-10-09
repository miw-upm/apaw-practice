package es.upm.miw.apaw.adapters.out.deadlinecalculator.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.deadlinecalculator.DayCountType;
import es.upm.miw.apaw.domain.model.deadlinecalculator.Deadline;
import es.upm.miw.apaw.domain.model.deadlinecalculator.DeadlineStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DeadlineEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String title;

    private String courtFileNumber;

    @Column(nullable = false)
    private LocalDate notificationDate;

    @Column(nullable = false)
    private Integer days;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayCountType dayCountType;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeadlineStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDate dueDate;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<NonWorkingDayEntity> nonWorkingDays;

    @Column(nullable = false)
    private UUID userId;

    public DeadlineEntity(Deadline deadline) {
        BeanUtils.copyProperties(deadline, this, "nonWorkingDays", "lawyer");
        this.nonWorkingDays = deadline.getNonWorkingDays().stream()
                .map(NonWorkingDayEntity::new)
                .toList();
        this.userId = deadline.getLawyer().getId();
    }

    public Deadline toDomain() {
        Deadline deadline = new Deadline();
        BeanUtils.copyProperties(this, deadline, "nonWorkingDays", "userId");
        deadline.setNonWorkingDays(new ArrayList<>(this.nonWorkingDays.stream()
                .map(NonWorkingDayEntity::toDomain)
                .toList()));
        deadline.setLawyer(UserSnapshot.builder().id(this.userId).build());
        return deadline;
    }
}
