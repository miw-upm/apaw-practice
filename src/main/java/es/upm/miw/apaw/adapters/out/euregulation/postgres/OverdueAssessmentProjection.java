package es.upm.miw.apaw.adapters.out.euregulation.postgres;

import java.time.LocalDate;
import java.util.UUID;

public interface OverdueAssessmentProjection {
    UUID getUserSnapshotId();

    Long getTotalAssessments();

    Long getOverdueCount();

    Long getDueSoonCount();

    LocalDate getNearestDeadline();

    Integer getDaysToNearestDeadline();
}
