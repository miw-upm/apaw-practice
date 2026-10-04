package es.upm.miw.apaw.domain.model.euregulation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
}
