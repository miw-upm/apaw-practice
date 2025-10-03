package es.upm.miw.apaw.domain.models.clothingstore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @NotBlank
    private String number;

    @NotNull
    private LocalDate issuedAt;

    @NotNull
    private BigDecimal tax;

    @NotNull
    private LocalDate dueDate;
}
