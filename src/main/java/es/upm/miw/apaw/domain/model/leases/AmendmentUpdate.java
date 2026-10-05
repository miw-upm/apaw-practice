package es.upm.miw.apaw.domain.model.leases;

import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AmendmentUpdate(Integer amendmentNumber,
                              @Pattern(regexp = ".*\\S.*", message = "must not be blank") String description,
                              LocalDate effectiveDate,
                              BigDecimal additionalAmount, Boolean approved, AmendmentType amendmentType) {
}
