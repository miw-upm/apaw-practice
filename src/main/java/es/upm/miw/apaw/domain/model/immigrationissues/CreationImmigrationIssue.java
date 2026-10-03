package es.upm.miw.apaw.domain.model.immigrationissues;

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
public class CreationImmigrationIssue {

    @NotBlank
    private String subject;

    @NotBlank
    private String clientNationality;

    private String clientImmigrationStatus;

    @NotNull
    private LocalDate responseDueDate;

    private BigDecimal estimatedCost;

    @NotEmpty
    private List<@NotNull UUID> lawBasisIds;

    @NotNull
    private UUID userId;
}