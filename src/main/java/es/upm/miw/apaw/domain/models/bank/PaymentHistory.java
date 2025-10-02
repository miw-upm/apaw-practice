package es.upm.miw.apaw.domain.models.bank;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentHistory {

    @NotNull
    private UUID historyId;
    @NotNull
    private BigDecimal paymentAmount;
    @NotNull
    private LocalDateTime paymentDate;
    @NotNull
    private Boolean paid;
}
