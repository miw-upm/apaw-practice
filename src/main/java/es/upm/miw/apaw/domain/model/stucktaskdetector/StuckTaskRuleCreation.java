package es.upm.miw.apaw.domain.model.stucktaskdetector;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StuckTaskRuleCreation {

    @NotBlank
    private String name;

    @NotBlank
    private String procedureKeyword;

    @NotNull
    private Integer thresholdDays;

    private BigDecimal penaltyAmount;

    private Boolean active;

    @NotNull
    private UUID userId;
}
