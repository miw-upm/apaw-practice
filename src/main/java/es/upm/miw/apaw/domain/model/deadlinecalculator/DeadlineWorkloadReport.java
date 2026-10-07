package es.upm.miw.apaw.domain.model.deadlinecalculator;

import es.upm.miw.apaw.domain.model.UserSnapshot;

import java.util.UUID;

public record DeadlineWorkloadReport(
        UUID userId,
        UserSnapshot userSnapshot,
        long expiredCount,
        long deadlineCount,
        long holidayAffectedCount
) {
    public DeadlineWorkloadReport(
            UUID userId,
            long expiredCount,
            long deadlineCount,
            long holidayAffectedCount
    ) {
        this(userId, null, expiredCount, deadlineCount, holidayAffectedCount);
    }
}
