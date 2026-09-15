package es.upm.miw.apaw.domain.models.legalprocedure;

import com.fasterxml.jackson.annotation.JsonProperty;
import es.upm.miw.apaw.domain.models.UserSnapshot;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LegalProcedure {

    @EqualsAndHashCode.Include
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull
    @Builder.Default
    private UUID id = UUID.randomUUID();

    @NotBlank
    private String title;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull
    @Builder.Default
    private LocalDate startedDate = LocalDate.now();

    private LocalDate closingDate;

    @NotNull
    private BigDecimal budget;

    private String budgetProposal;

    @NotNull
    @Builder.Default
    private Boolean vatIncluded = false;

    @NotEmpty
    @Valid
    @Builder.Default
    private List<@Valid LegalTask> legalTasks = new ArrayList<>();

    @NotNull
    @Valid
    private UserSnapshot userSnapshot;
}