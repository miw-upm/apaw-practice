package es.upm.miw.apaw.domain.model.evidencemanagement;

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
public class CustodianActivityReport {
    private UserSnapshot custodian;
    private long recordsCount;
    private long evidencesCount;
    private long totalDurationMinutes;

    public CustodianActivityReport(UUID custodianId, long recordsCount, long evidencesCount,
                                   long totalDurationMinutes) {
        this(UserSnapshot.builder().id(custodianId).build(), recordsCount, evidencesCount, totalDurationMinutes);
    }
}
