package es.upm.miw.apaw.adapters.out.legalprocedure.postgres;

import es.upm.miw.apaw.domain.models.UserSnapshot;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalProcedure;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LegalProcedureEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private LocalDate startedDate;

    private LocalDate closingDate;

    @Column(nullable = false)
    private BigDecimal budget;

    private String budgetProposal;

    @Column(nullable = false)
    private Boolean vatIncluded;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "legal_procedure_id")
    private List<LegalTaskEntity> legalTasks;

    @Column(nullable = false)
    private UUID userId;

    public LegalProcedureEntity(LegalProcedure legalProcedure) {
        BeanUtils.copyProperties(legalProcedure, this, "legalTasks", "userSnapshot");
        this.legalTasks = legalProcedure.getLegalTasks().stream()
                .map(LegalTaskEntity::new)
                .toList();
        this.userId = legalProcedure.getUserSnapshot().getId();
    }

    public LegalProcedure toDomain() {
        LegalProcedure legalProcedure = new LegalProcedure();
        BeanUtils.copyProperties(this, legalProcedure, "legalTasks", "userId");
        legalProcedure.setLegalTasks(new ArrayList<>(this.legalTasks.stream()
                .map(LegalTaskEntity::toDomain)
                .toList()));
        legalProcedure.setUserSnapshot(UserSnapshot.builder().id(this.userId).build());
        return legalProcedure;
    }

    public LegalProcedure toSummary() {
        LegalProcedure legalProcedure = new LegalProcedure();
        BeanUtils.copyProperties(this, legalProcedure, "legalTasks", "userId");
        legalProcedure.setUserSnapshot(UserSnapshot.builder().id(this.userId).build());
        return legalProcedure.ofSummary();
    }
}
