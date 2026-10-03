package es.upm.miw.apaw.adapters.out.immigrationissues.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.immigrationissues.ImmigrationIssue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
public class ImmigrationIssueEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String subject;

    @Column(nullable = false)
    private String clientNationality;

    private String clientImmigrationStatus;

    @Column(nullable = false)
    private LocalDateTime openedAt;

    @Column(nullable = false)
    private LocalDate responseDueDate;

    @Column(nullable = false)
    private BigDecimal estimatedCost;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<LawBasisEntity> lawBases;

    @Column(nullable = false)
    private UUID userId;

    public ImmigrationIssueEntity(ImmigrationIssue immigrationIssue) {
        BeanUtils.copyProperties(immigrationIssue, this, "lawBases", "userSnapshot");
        this.lawBases = immigrationIssue.getLawBases().stream()
                .map(LawBasisEntity::new)
                .toList();
        this.userId = immigrationIssue.getUserSnapshot().getId();
    }

    public ImmigrationIssue toDomain() {
        ImmigrationIssue immigrationIssue = new ImmigrationIssue();
        BeanUtils.copyProperties(this, immigrationIssue, "lawBases", "userId");
        immigrationIssue.setLawBases(new ArrayList<>(this.lawBases.stream()
                .map(LawBasisEntity::toDomain)
                .toList()));
        immigrationIssue.setUserSnapshot(UserSnapshot.builder().id(this.userId).build());
        return immigrationIssue;
    }
}