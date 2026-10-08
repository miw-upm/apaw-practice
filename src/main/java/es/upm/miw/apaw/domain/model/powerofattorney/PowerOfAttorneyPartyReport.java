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
    private UserSnapshot userSnapshot;
    private long totalPowerOfAttorneys;
    private long principalCount;
    private long attorneyCount;

    public PowerOfAttorneyPartyReport(
            UUID userId, long totalPowerOfAttorneys, long principalCount, long attorneyCount) {
        this.userSnapshot = UserSnapshot.builder().id(userId).build();
        this.totalPowerOfAttorneys = totalPowerOfAttorneys;
        this.principalCount = principalCount;
        this.attorneyCount = attorneyCount;
    }
}
