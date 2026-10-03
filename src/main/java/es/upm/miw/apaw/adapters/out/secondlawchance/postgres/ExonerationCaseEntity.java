package es.upm.miw.apaw.adapters.out.secondlawchance.postgres;

import es.upm.miw.apaw.domain.model.secondlawchance.ExonerationCase;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

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

    private String lawyer;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<DebtEntity> debts;

    @Column(nullable = false)
    private UUID userId;

    public ExonerationCaseEntity(ExonerationCase exonerationCase) {
        BeanUtils.copyProperties(exonerationCase, this, "debts", "userSnapshot");
        this.userId = exonerationCase.getUserSnapshot().getId();
    }
}
