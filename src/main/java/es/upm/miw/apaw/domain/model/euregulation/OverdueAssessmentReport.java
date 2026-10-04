package es.upm.miw.apaw.domain.model.euregulation;

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
public class OverdueAssessmentReport {
    private UUID userSnapshotId;
    private long totalAssessments;
    private long overdueCount;
    private long dueSoonCount;
    private LocalDate nearestDeadline;
    private int daysToNearestDeadline;
}
