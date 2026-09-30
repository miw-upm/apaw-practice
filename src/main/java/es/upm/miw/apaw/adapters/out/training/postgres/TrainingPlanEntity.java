package es.upm.miw.apaw.adapters.out.training.postgres;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TrainingPlanEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String planCode;

    @Column
    private LocalDate approvalDate;

    @Column
    private LocalDate endDate;

    @Column
    private BigDecimal evaluationScore;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<CourseEntity> courses;

    @ElementCollection
    private List<UUID> userIds;
}
