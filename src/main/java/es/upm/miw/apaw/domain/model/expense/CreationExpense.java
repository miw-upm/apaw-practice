package es.upm.miw.apaw.domain.model.expense;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreationExpense {

    @NotBlank
    private String reference;

    @NotNull
    private BigDecimal amount;

    @NotBlank
    private String description;

    private String category;

    @NotNull
    private UUID supplierId;

    @NotNull
    private UUID applicantId;
}