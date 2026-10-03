package es.upm.miw.apaw.domain.model.secondlawchance;

import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DebtPatch(
        @Pattern(regexp = ".*\\S.*") String contractNumber,
        LocalDate issueDate,
        @Pattern(regexp = ".*\\S.*") String creditorName,
        BigDecimal amount,
        CreditorType type,
        Boolean guarantee) {
}
