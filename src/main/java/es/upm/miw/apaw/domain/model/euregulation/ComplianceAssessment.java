package es.upm.miw.apaw.domain.model.euregulation;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ComplianceAssessment {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotNull
    private String responsibleLawyer;

    @NotNull
    private ComplianceLevel complianceLevel;

    @NotNull
    private RiskLevel riskLevel;

    @NotNull
    private LocalDate assessmentDate;

    private LocalDate complianceDeadline;

    private LocalDate nextReviewDate;

    private String correctiveActions;

    private String supportingDocumentation;

    private String notes;

    @NotNull
    @Builder.Default
    private Boolean aiGenerated = false;

    @NotNull
    private UserSnapshot userSnapshot;

    @Builder.Default
    private List<EURegulation> euRegulations = new ArrayList<>();

    public void doDefault() {
        this.id = UUID.randomUUID();
        if (this.riskLevel == null) {
            this.riskLevel = RiskLevel.LOW;
        }
        if (this.assessmentDate == null) {
            this.assessmentDate = LocalDate.now();
        }
        if (this.aiGenerated == null) {
            this.aiGenerated = false;
        }
    }
}
