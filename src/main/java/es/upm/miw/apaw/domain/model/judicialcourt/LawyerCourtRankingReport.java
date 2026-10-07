package es.upm.miw.apaw.domain.model.judicialcourt;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LawyerCourtRankingReport {
    private UserSnapshot lawyer;
    private Long totalJudicialCourts;
}
