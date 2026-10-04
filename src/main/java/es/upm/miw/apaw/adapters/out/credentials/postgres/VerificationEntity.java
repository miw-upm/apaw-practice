package es.upm.miw.apaw.adapters.out.credentials.postgres;

import es.upm.miw.apaw.domain.model.credentials.Verification;
import es.upm.miw.apaw.domain.model.credentials.VerificationStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VerificationEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime verifiedAt;

    @Column(nullable = false)
    private String method;

    private String name;

    private String notes;

    private BigDecimal score;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VerificationStatus verificationStatus;

    public VerificationEntity(Verification verification) {
        BeanUtils.copyProperties(verification, this);
    }

    public Verification toDomain() {
        Verification verification = new Verification();
        BeanUtils.copyProperties(this, verification);
        return verification;
    }
}