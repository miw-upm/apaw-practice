package es.upm.miw.apaw.domain.model.leases;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Amendment {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotNull
    private Integer amendmentNumber;

    @NotBlank
    private String description;

    @NotNull
    private LocalDate effectiveDate;

    private BigDecimal additionalAmount;

    private Boolean approved;

    @NotNull
    private AmendmentType amendmentType;

    public void doDefault() {
        this.id = UUID.randomUUID();
        if (this.approved == null) {
            this.approved = false;
        }
    }

    public void patch(AmendmentUpdate update) {
        Optional.ofNullable(update.amendmentNumber()).ifPresent(this::setAmendmentNumber);
        Optional.ofNullable(update.description()).ifPresent(this::setDescription);
        Optional.ofNullable(update.effectiveDate()).ifPresent(this::setEffectiveDate);
        Optional.ofNullable(update.additionalAmount()).ifPresent(this::setAdditionalAmount);
        Optional.ofNullable(update.approved()).ifPresent(this::setApproved);
        Optional.ofNullable(update.amendmentType()).ifPresent(this::setAmendmentType);
    }
}
