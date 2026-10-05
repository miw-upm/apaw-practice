package es.upm.miw.apaw.domain.model.euregulation;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OverdueComplianceReport {
    private UUID userSnapshotId;
    private String firstName;
    private String mobile;
    private long totalAssessments;
    private long overdueCount;
    private long dueSoonCount;
    private LocalDate nearestDeadline;
    private int daysToNearestDeadline;

    public static OverdueComplianceReport from(OverdueAssessmentReport report, UserSnapshot user) {
        return OverdueComplianceReport.builder()
                .userSnapshotId(report.getUserSnapshotId())
                .firstName(user == null ? null : user.getFirstName())
                .mobile(user == null ? null : user.getMobile())
                .totalAssessments(report.getTotalAssessments())
                .overdueCount(report.getOverdueCount())
                .dueSoonCount(report.getDueSoonCount())
                .nearestDeadline(report.getNearestDeadline())
                .daysToNearestDeadline(report.getDaysToNearestDeadline())
                .build();
    }
}
