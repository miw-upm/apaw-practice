package es.upm.miw.apaw.adapters.out.secondlawchance.postgres;

import es.upm.miw.apaw.domain.model.secondlawchance.CreditorType;
import es.upm.miw.apaw.domain.model.secondlawchance.Debt;
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
public class DebtEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String contractNumber;

    @Column(nullable = false)
    private LocalDate issueDate;

    @Column(nullable = false)
    private String creditorName;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CreditorType type;

    @Column(nullable = false)
    private Boolean guarantee;

    public DebtEntity(Debt debt) {
        BeanUtils.copyProperties(debt, this);
    }

    public Debt toDomain() {
        Debt debt = new Debt();
        BeanUtils.copyProperties(this, debt);
        return debt;
    }
}
