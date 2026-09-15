package es.upm.miw.apaw.domain.models.legalprocedure;

import es.upm.miw.apaw.domain.models.UserSnapshot;
import jakarta.validation.Valid;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LegalProcedure {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String title;

    private LocalDate startedDate;

    private LocalDate closingDate;

    @NotNull
    private BigDecimal budget;

    private String budgetProposal;

    private Boolean vatIncluded;

    @NotEmpty
    @Valid
    private List<@Valid LegalTask> legalTasks;

    @NotNull
    @Valid
    private UserSnapshot userSnapshot;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.startedDate = LocalDate.now();
        if (this.vatIncluded == null) {
            this.vatIncluded = false;
        }
    }
}