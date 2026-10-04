package es.upm.miw.apaw.adapters.in.reports;

import es.upm.miw.apaw.domain.model.euregulation.OverdueComplianceReport;

import java.time.LocalDate;
import java.util.UUID;

public record OverdueComplianceReportDto(
        UUID userSnapshotId,
        String firstName,
        String mobile,
        long totalAssessments,
        long overdueCount,
        long dueSoonCount,
        LocalDate nearestDeadline,
        int daysToNearestDeadline
) {
    public static OverdueComplianceReportDto fromDomain(OverdueComplianceReport report) {
        return new OverdueComplianceReportDto(
                report.getUserSnapshotId(),
                report.getFirstName(),
                report.getMobile(),
                report.getTotalAssessments(),
                report.getOverdueCount(),
                report.getDueSoonCount(),
                report.getNearestDeadline(),
                report.getDaysToNearestDeadline());
    }
}
