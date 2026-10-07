package es.upm.miw.apaw.domain.model.stucktaskdetector;

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
public class StuckTaskRuleAlertReport {
    private String ruleName;
    private UserSnapshot createdByUser;
    private long totalAlertCount;
    private long unresolvedAlertCount; // mostrar las no resueltas es lo mas prioritario

    public StuckTaskRuleAlertReport(
            String ruleName, UUID createdByUserId, long totalAlertCount, long unresolvedAlertCount) {
        this.ruleName = ruleName;
        this.createdByUser = UserSnapshot.builder().id(createdByUserId).build();
        this.totalAlertCount = totalAlertCount;
        this.unresolvedAlertCount = unresolvedAlertCount;
    }
}
