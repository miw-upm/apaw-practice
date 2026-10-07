package es.upm.miw.apaw.domain.model.credentials;

import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VerificationPatch(
        LocalDateTime verifiedAt,
        @Pattern(regexp = ".*\\S.*") String method,
        String name,
        String notes,
        BigDecimal score,
        VerificationStatus verificationStatus
) {
}