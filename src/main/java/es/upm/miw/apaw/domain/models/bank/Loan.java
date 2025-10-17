package es.upm.miw.apaw.domain.models.bank;

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
public class Loan {

        private UUID id;
        @NotNull
        private BigDecimal quantity;
        @NotNull
        private Double interestRate;
        @NotBlank
        private String condition;
}
