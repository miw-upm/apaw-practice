package es.upm.miw.apaw.adapters.out.leases.postgres;

import es.upm.miw.apaw.domain.model.leases.Amendment;
import es.upm.miw.apaw.domain.model.leases.AmendmentType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AmendmentEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private Integer amendmentNumber;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate effectiveDate;

    private BigDecimal additionalAmount;

    @Column(nullable = false)
    private Boolean approved;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AmendmentType amendmentType;

    public AmendmentEntity(Amendment amendment) {
        BeanUtils.copyProperties(amendment, this);
    }

    public Amendment toDomain() {
        Amendment amendment = new Amendment();
        BeanUtils.copyProperties(this, amendment);
        return amendment;
    }
}
