package es.upm.miw.apaw.adapters.in.euregulation;

import es.upm.miw.apaw.domain.model.euregulation.ComplianceAssessmentPatch;
import es.upm.miw.apaw.domain.model.euregulation.ComplianceLevel;
import es.upm.miw.apaw.domain.model.euregulation.RiskLevel;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ComplianceAssessmentPatchDto(
        @Pattern(regexp = ".*\\S.*") String responsibleLawyer,
        LocalDate complianceDeadline,
        LocalDate nextReviewDate,
        String correctiveActions,
        String supportingDocumentation,
        String notes,
        Boolean aiGenerated,
        ComplianceLevel complianceLevel,
        RiskLevel riskLevel,
        UUID userId,
        List<UUID> euRegulationIds
) {
    public ComplianceAssessmentPatch toDomain() {
        return new ComplianceAssessmentPatch(
                this.responsibleLawyer,
                this.complianceDeadline,
                this.nextReviewDate,
                this.correctiveActions,
                this.supportingDocumentation,
                this.notes,
                this.aiGenerated,
                this.complianceLevel,
                this.riskLevel);
    }
}
