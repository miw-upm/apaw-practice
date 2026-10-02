package es.upm.miw.apaw.adapters.out.secondlawchance.postgres;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ExonerationCaseEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String caseNumber;

    @Column(nullable = false)
    private LocalDate filingDate;

    private LocalDate resolutionDate;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    private String lawyer;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<DebtEntity> debts;

    @Column(nullable = false)
    private UUID userId;
}
