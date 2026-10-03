package es.upm.miw.apaw.domain.model.stucktaskdetector;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StuckTaskRule {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String procedureKeyword;

    @NotNull
    private Integer thresholdDays;

    private BigDecimal penaltyAmount;

    private Boolean active;

    private LocalDate createdAt;

    private UserSnapshot createdByUser;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDate.now();
        if (this.active == null) {
            this.active = true;
        }
    }
}
