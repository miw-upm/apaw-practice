package es.upm.miw.apaw.domain.models.legalprocedure;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class CreationLegalProcedure {

    @NotBlank
    private String title;

    private LocalDate closingDate;

    @NotNull
    private BigDecimal budget;

    private String budgetProposal;

    private Boolean vatIncluded;

    @NotEmpty
    private List<UUID> legalTaskIds;

    @NotNull
    private UUID userId;
}
