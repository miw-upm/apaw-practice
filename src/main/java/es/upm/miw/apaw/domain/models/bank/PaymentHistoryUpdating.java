package es.upm.miw.apaw.domain.models.bank;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentHistoryUpdating {

    @NotNull
    private UUID id;
    @NotNull
    private Boolean paid;
}
