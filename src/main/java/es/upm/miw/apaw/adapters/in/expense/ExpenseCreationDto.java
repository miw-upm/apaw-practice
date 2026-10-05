package es.upm.miw.apaw.adapters.in.expense;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseCreationDto {

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