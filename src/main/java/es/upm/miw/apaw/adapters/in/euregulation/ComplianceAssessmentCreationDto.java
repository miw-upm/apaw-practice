package es.upm.miw.apaw.adapters.in.euregulation;

import es.upm.miw.apaw.domain.model.euregulation.ComplianceAssessment;
import es.upm.miw.apaw.domain.model.euregulation.ComplianceLevel;
import es.upm.miw.apaw.domain.model.euregulation.RiskLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ComplianceAssessmentCreationDto(
        @NotBlank String responsibleLawyer,
        LocalDate complianceDeadline,
        LocalDate nextReviewDate,
        String correctiveActions,
        String supportingDocumentation,
        String notes,
        Boolean aiGenerated,
        @NotNull ComplianceLevel complianceLevel,
        @NotNull RiskLevel riskLevel,
        @NotNull UUID userId,
        @NotNull List<@NotNull UUID> euRegulationIds
) {
    public ComplianceAssessment toDomain() {
        return ComplianceAssessment.builder()
                .responsibleLawyer(this.responsibleLawyer)
                .complianceDeadline(this.complianceDeadline)
                .nextReviewDate(this.nextReviewDate)
                .correctiveActions(this.correctiveActions)
                .supportingDocumentation(this.supportingDocumentation)
                .notes(this.notes)
                .aiGenerated(this.aiGenerated)
                .complianceLevel(this.complianceLevel)
                .riskLevel(this.riskLevel)
                .build();
    }
}
