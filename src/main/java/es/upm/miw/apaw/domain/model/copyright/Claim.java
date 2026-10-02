package es.upm.miw.apaw.domain.model.copyright;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Claim {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String number;

    private LocalDateTime filingDate;

    @NotNull
    private BigDecimal requestedCompensation;

    private Boolean urgent;

    private String resolutionNotes;

    private TaskStatus taskStatus;

    private UserSnapshot defendant;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.filingDate = LocalDateTime.now();
        if (this.urgent == null) {
            this.urgent = false;
        }
        if (this.taskStatus == null) {
            this.taskStatus = TaskStatus.CURRENT;
        }
    }
}
