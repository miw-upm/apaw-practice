package es.upm.miw.apaw.domain.model.training;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TrainingPlan {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String planCode;

    private LocalDate approvalDate;

    private LocalDate endDate;

    private BigDecimal evaluationScore;

    private List<Course> courses;

    private List<UserSnapshot> userSnapshots;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.approvalDate = LocalDate.now();
    }

    public TrainingPlan ofSummary() {
        return TrainingPlan.builder()
                .id(this.id)
                .planCode(this.planCode)
                .approvalDate(this.approvalDate)
                .endDate(this.endDate)
                .evaluationScore(this.evaluationScore)
                .userSnapshots(this.userSnapshots == null ? null : this.userSnapshots.stream()
                        .map(user -> UserSnapshot.builder()
                                .id(user.getId())
                                .mobile(user.getMobile())
                                .firstName(user.getFirstName())
                                .build())
                        .toList())
                .build();
    }
}
