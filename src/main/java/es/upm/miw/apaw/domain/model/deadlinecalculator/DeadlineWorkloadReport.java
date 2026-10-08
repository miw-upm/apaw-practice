package es.upm.miw.apaw.domain.model.deadlinecalculator;

import es.upm.miw.apaw.domain.model.UserSnapshot;

import java.util.UUID;

public record DeadlineWorkloadReport(
        UUID userId,
        UserSnapshot userSnapshot,
        long expiredDeadlineCount,
        long totalDeadlineCount,
        long holidayAffectedDeadlineCount
) {
    public DeadlineWorkloadReport(
            UUID userId,
            long expiredDeadlineCount,
            long totalDeadlineCount,
            long holidayAffectedDeadlineCount
    ) {
        this(userId, null, expiredDeadlineCount, totalDeadlineCount, holidayAffectedDeadlineCount);
    }
}
