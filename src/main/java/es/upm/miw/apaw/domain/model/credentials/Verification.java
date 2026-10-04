package es.upm.miw.apaw.domain.model.credentials;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Verification {

    @EqualsAndHashCode.Include
    private UUID id;

    private LocalDateTime createdAt;

    private LocalDateTime verifiedAt;

    private String method;

    private String name;

    private String notes;

    private BigDecimal score;

    private VerificationStatus verificationStatus;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        if (this.verificationStatus == null) {
            this.verificationStatus = VerificationStatus.PENDING;
        }
    }
}