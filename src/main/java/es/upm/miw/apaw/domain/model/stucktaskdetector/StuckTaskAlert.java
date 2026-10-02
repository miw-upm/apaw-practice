package es.upm.miw.apaw.domain.model.stucktaskdetector;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StuckTaskAlert {

    @EqualsAndHashCode.Include
    private UUID id;

    private String reference;

    private LocalDate detectedAt;

    private LocalDate resolvedAt;

    private Boolean escalated;

    private String resolutionNotes;

    @NotNull
    private StuckTaskRule stuckTaskRule;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.detectedAt = LocalDate.now();
        if (this.escalated == null) {
            this.escalated = false;
        }
    }
}
