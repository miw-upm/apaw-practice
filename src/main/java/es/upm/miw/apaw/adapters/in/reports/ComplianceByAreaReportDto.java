package es.upm.miw.apaw.adapters.in.reports;

import es.upm.miw.apaw.domain.model.euregulation.ApplicationArea;
import es.upm.miw.apaw.domain.model.euregulation.ComplianceByAreaReport;

import java.math.BigDecimal;

public record ComplianceByAreaReportDto(
        ApplicationArea applicationArea,
        long totalAssessments,
        long compliantCount,
        long partiallyCompliantCount,
        long nonCompliantCount,
        long pendingReviewCount,
        BigDecimal complianceRate
) {
    public static ComplianceByAreaReportDto fromDomain(ComplianceByAreaReport report) {
        return new ComplianceByAreaReportDto(
                report.getApplicationArea(),
                report.getTotalAssessments(),
                report.getCompliantCount(),
                report.getPartiallyCompliantCount(),
                report.getNonCompliantCount(),
                report.getPendingReviewCount(),
                report.getComplianceRate());
    }
}
