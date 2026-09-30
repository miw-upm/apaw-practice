package es.upm.miw.apaw.adapters.out.training.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

    @Column
    private String planCode;

    @Column
    private LocalDate approvalDate;

    @Column
    private LocalDate endDate;

    @Column
    private BigDecimal evaluationScore;

    @OneToMany
    private List<CourseEntity> courses;

    @OneToMany
    private List<UserSnapshot> userSnapshots;
}
