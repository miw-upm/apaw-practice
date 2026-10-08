package es.upm.miw.apaw.domain.model.probate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.time.LocalDate;

public record HeirUpdate(
        String fullName,
        String nationalId,
        LocalDate birthDate,
        @DecimalMin("0.00") @DecimalMax("100.00") BigDecimal sharePercentage,
        HeirStatus heirStatus,
        String contactEmail) {
}