package es.upm.miw.apaw.domain.model.courthearing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourtHearingByCourtReport {
    private String courtName;
    private long totalHearingCount;
    private long scheduledHearingCount;
}