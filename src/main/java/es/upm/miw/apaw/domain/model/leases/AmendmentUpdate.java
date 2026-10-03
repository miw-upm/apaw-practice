package es.upm.miw.apaw.domain.model.leases;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AmendmentUpdate(Integer amendmentNumber, String description, LocalDate effectiveDate,
                              BigDecimal additionalAmount, Boolean approved, AmendmentType amendmentType) {
}
