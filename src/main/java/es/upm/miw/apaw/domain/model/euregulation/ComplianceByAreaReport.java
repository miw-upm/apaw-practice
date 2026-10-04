package es.upm.miw.apaw.domain.model.euregulation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplianceByAreaReport {
    private ApplicationArea applicationArea;
    private long totalAssessments;
    private long compliantCount;
    private long partiallyCompliantCount;
    private long nonCompliantCount;
    private long pendingReviewCount;
    private BigDecimal complianceRate;

    public ComplianceByAreaReport(
            ApplicationArea applicationArea,
            Long totalAssessments,
            Long compliantCount,
            Long partiallyCompliantCount,
            Long nonCompliantCount,
            Long pendingReviewCount) {
        this.applicationArea = applicationArea;
        this.totalAssessments = totalAssessments;
        this.compliantCount = compliantCount;
        this.partiallyCompliantCount = partiallyCompliantCount;
        this.nonCompliantCount = nonCompliantCount;
        this.pendingReviewCount = pendingReviewCount;
        this.complianceRate = BigDecimal.valueOf(compliantCount)
                .divide(BigDecimal.valueOf(totalAssessments), 10, RoundingMode.HALF_UP)
                .stripTrailingZeros();
    }
}
