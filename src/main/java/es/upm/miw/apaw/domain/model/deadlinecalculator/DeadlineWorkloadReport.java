package es.upm.miw.apaw.domain.model.deadlinecalculator;

import es.upm.miw.apaw.domain.model.UserSnapshot;

import java.util.UUID;

public record DeadlineWorkloadReport(
        UserSnapshot lawyer,
        long expiredDeadlineCount,
        long totalDeadlineCount,
        long holidayAffectedDeadlineCount
) {
    public DeadlineWorkloadReport(
            UUID lawyerId,
            long expiredDeadlineCount,
            long totalDeadlineCount,
            long holidayAffectedDeadlineCount
    ) {
        this(UserSnapshot.builder().id(lawyerId).build(),
                expiredDeadlineCount, totalDeadlineCount, holidayAffectedDeadlineCount);
    }
}
