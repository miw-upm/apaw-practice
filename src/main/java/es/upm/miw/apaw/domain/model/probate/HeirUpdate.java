package es.upm.miw.apaw.domain.model.probate;

import java.math.BigDecimal;
import java.time.LocalDate;

public record HeirUpdate(
        String fullName,
        String nationalId,
        LocalDate birthDate,
        BigDecimal sharePercentage,
        HeirStatus heirStatus,
        String contactEmail) {
}