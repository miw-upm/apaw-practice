package es.upm.miw.apaw.adapters.out.evidencemanagement.postgres;

import es.upm.miw.apaw.domain.model.evidencemanagement.EvidenceStatus;
import es.upm.miw.apaw.domain.model.evidencemanagement.EvidenceType;
import es.upm.miw.apaw.domain.model.evidencemanagement.Evidence;
import jakarta.persistence.*;
import lombok.*;

import org.springframework.beans.BeanUtils;

import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EvidenceEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EvidenceType evidenceType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EvidenceStatus status;

    @Column(nullable = false)
    private LocalDateTime collectionDate;

    private String source;

    @Column(nullable = false)
    private Boolean confidential;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "evidence_id")
    @Builder.Default
    private List<CustodyRecordEntity> custodyRecords = new ArrayList<>();

    public EvidenceEntity(Evidence evidence) {
        BeanUtils.copyProperties(evidence, this, "custodyRecords");
        this.custodyRecords = evidence.getCustodyRecords().stream()
                .map(CustodyRecordEntity::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Evidence toDomain() {
        Evidence evidence = new Evidence();
        BeanUtils.copyProperties(this, evidence, "custodyRecords");
        evidence.setCustodyRecords(this.custodyRecords.stream()
                .map(CustodyRecordEntity::toDomain)
                .toList());
        return evidence;
    }
}
