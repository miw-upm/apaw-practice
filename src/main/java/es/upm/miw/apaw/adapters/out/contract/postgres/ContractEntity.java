package es.upm.miw.apaw.adapters.out.contract.postgres;

import es.upm.miw.apaw.domain.model.contract.Contract;
import es.upm.miw.apaw.domain.model.contract.ContractType;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class ContractEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContractType type;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal amount;

    @Column(nullable = false)
    private Boolean automaticRenewal;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<ClauseEntity> clauses;

    @Column(nullable = false)
    private UUID userId;

    private String userMobile;

    private String userFirstName;

    public ContractEntity(Contract contract) {
        BeanUtils.copyProperties(contract, this, "clauses", "userSnapshot");

        this.clauses = contract.getClauses().stream()
                .map(ClauseEntity::new)
                .toList();

        this.userId = contract.getUserSnapshot().getId();
    }

    public Contract toDomain() {
        Contract contract = new Contract();

        BeanUtils.copyProperties(this, contract, "clauses", "userId");

        contract.setClauses(new ArrayList<>(this.clauses.stream()
                .map(ClauseEntity::toDomain)
                .toList()));

        contract.setUserSnapshot(UserSnapshot.builder()
                .id(this.userId)
                .build());

        return contract;
    }
}
