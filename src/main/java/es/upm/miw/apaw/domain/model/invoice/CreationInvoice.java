package es.upm.miw.apaw.domain.model.invoice;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreationInvoice {

    @NotNull
    private BigDecimal vatRate;

    @NotEmpty
    private List<@NotNull UUID> legalServiceIds;

    @NotNull
    private UUID userId;
}

