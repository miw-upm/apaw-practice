package es.upm.miw.apaw.domain.model.euregulation;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiskExposureReport {
    private UUID userSnapshotId;
    private String firstName;
    private String mobile;
    private long totalAssessments;
    private long highRiskCount;
    private long mediumRiskCount;
    private long lowRiskCount;
    private long nonCompliantCount;
    private long riskScore;

    public RiskExposureReport(
            UUID userSnapshotId,
            Long totalAssessments,
            Long highRiskCount,
            Long mediumRiskCount,
            Long lowRiskCount,
            Long nonCompliantCount) {
        this.userSnapshotId = userSnapshotId;
        this.totalAssessments = totalAssessments;
        this.highRiskCount = highRiskCount;
        this.mediumRiskCount = mediumRiskCount;
        this.lowRiskCount = lowRiskCount;
        this.nonCompliantCount = nonCompliantCount;
        this.riskScore = 3 * highRiskCount + 2 * mediumRiskCount + lowRiskCount;
    }

    public static RiskExposureReport from(RiskExposureReport report, UserSnapshot user) {
        return RiskExposureReport.builder()
                .userSnapshotId(report.getUserSnapshotId())
                .firstName(user == null ? null : user.getFirstName())
                .mobile(user == null ? null : user.getMobile())
                .totalAssessments(report.getTotalAssessments())
                .highRiskCount(report.getHighRiskCount())
                .mediumRiskCount(report.getMediumRiskCount())
                .lowRiskCount(report.getLowRiskCount())
                .nonCompliantCount(report.getNonCompliantCount())
                .riskScore(report.getRiskScore())
                .build();
    }
}
