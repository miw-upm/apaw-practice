package es.upm.miw.apaw.domain.model.stucktaskdetector;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StuckTaskAlertCreation {

    private String reference;

    @NotNull
    private UUID stuckTaskRuleId;
}