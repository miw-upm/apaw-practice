package es.upm.miw.apaw.domain.model.probate;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estate {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String fileNumber;

    private LocalDate openedDate;

    @NotBlank
    private String deceasedName;

    @NotNull
    private BigDecimal netValue;

    private Boolean lastWill;

    private LocalDate closingDate;

    private List<Heir> heirs;

    private UserSnapshot userSnapshot;

    public void applyDefaults() {
        this.id = UUID.randomUUID();
        this.openedDate = LocalDate.now();
        if (this.lastWill == null) {
            this.lastWill = false;
        }
    }

    public boolean isClosed() {
        return this.closingDate != null;
    }
}
