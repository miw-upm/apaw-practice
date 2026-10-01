package es.upm.miw.apaw.domain.model.contract;

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
public class CreationContract {

    @NotBlank
    private String title;

    private ContractType type;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal amount;

    private Boolean automaticRenewal;

    @NotEmpty
    private List<@NotNull UUID> clauseIds;

    @NotNull
    private UUID userId;
}