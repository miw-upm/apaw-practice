package es.upm.miw.apaw.domain.model.invoice;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record LegalServiceUpdate(
        @NotNull UUID id,
        @NotNull BigDecimal fee
) {
}
