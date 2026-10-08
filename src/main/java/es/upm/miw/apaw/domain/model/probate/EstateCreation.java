package es.upm.miw.apaw.domain.model.probate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstateCreation {

    @NotBlank
    private String fileNumber;

    @NotBlank
    private String deceasedName;

    @NotNull
    private BigDecimal netValue;

    private Boolean lastWill;

    private LocalDate closingDate;

    @NotEmpty
    private List<@NotNull UUID> heirIds;

    @NotNull
    private UUID userId;
}
