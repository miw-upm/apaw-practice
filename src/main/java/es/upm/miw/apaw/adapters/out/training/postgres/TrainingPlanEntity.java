package es.upm.miw.apaw.adapters.out.training.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.training.TrainingPlan;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @ElementCollection(fetch = FetchType.EAGER)
    private List<UUID> userIds;

    public TrainingPlanEntity(TrainingPlan trainingPlan) {
        BeanUtils.copyProperties(trainingPlan, this, "courses", "userSnapshots");
        this.courses = trainingPlan.getCourses().stream()
                .map(CourseEntity::new)
                .toList();
        this.userIds = trainingPlan.getUserSnapshots().stream()
                .map(UserSnapshot::getId)
                .toList();
    }

    public TrainingPlan toDomain() {
        TrainingPlan trainingPlan = new TrainingPlan();
        BeanUtils.copyProperties(this, trainingPlan, "courses", "userIds");
        trainingPlan.setCourses(new ArrayList<>(this.courses.stream()
                .map(CourseEntity::toDomain)
                .toList()));
        trainingPlan.setUserSnapshots(this.userIds.stream()
                .map(id -> UserSnapshot.builder().id(id).build())
                .toList());
        return trainingPlan;
    }
}

