package es.upm.miw.apaw.domain.model.euregulation;

import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record ComplianceAssessmentPatch(
        @Pattern(regexp = ".*\\S.*") String responsibleLawyer,
        LocalDate complianceDeadline,
        LocalDate nextReviewDate,
        String correctiveActions,
        String supportingDocumentation,
        String notes,
        Boolean aiGenerated,
        ComplianceLevel complianceLevel,
        RiskLevel riskLevel
) {
}
