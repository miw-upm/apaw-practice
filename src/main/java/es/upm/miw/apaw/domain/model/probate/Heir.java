package es.upm.miw.apaw.domain.model.probate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
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
public class Heir {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String fullName;

    @NotBlank
    private String nationalId;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("100.00")
    private BigDecimal sharePercentage;

    private HeirStatus heirStatus;

    private String contactEmail;

    public void applyDefaults() {
        this.id = UUID.randomUUID();
        if (this.heirStatus == null) {
            this.heirStatus = HeirStatus.PENDING;
        }
    }
}
