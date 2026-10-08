package es.upm.miw.apaw.domain.model.powerofattorney;

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
public class PowerOfAttorneyPartyReport {
    private String userId;
    private long totalPowerOfAttorneysPresent;
    private long principalCount;
    private long attorneyCount;

    public PowerOfAttorneyPartyReport(
            UUID userId, long totalPowerOfAttorneys, long principalCount, long attorneyCount) {
        String value = userId.toString();
        this.userId = value.substring(value.lastIndexOf('-') + 1);
        this.totalPowerOfAttorneysPresent = totalPowerOfAttorneys;
        this.principalCount = principalCount;
        this.attorneyCount = attorneyCount;
    }
}
