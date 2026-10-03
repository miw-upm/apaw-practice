package es.upm.miw.apaw.adapters.out.euregulation.postgres;

import es.upm.miw.apaw.domain.model.euregulation.EURegulation;
import es.upm.miw.apaw.domain.model.euregulation.ApplicationArea;
import es.upm.miw.apaw.domain.model.euregulation.IssuingBody;
import es.upm.miw.apaw.domain.model.euregulation.LegalInstrumentType;
import es.upm.miw.apaw.domain.model.euregulation.LegalStatus;
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
public class EURegulationEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private String regulationName;

    @Column(nullable = false, unique = true)
    private Integer sequentialId;

    @Column(nullable = false, unique = true)
    private String officialReferenceNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LegalInstrumentType instrumentType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationArea applicationArea;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LegalStatus legalStatus;

    @Column(nullable = false)
    private LocalDate entryIntoForceDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IssuingBody issuingBody;

    private LocalDate transpositionDeadline;

    private String officialJournalLink;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @ManyToMany(mappedBy = "euRegulations", fetch = FetchType.LAZY)
    private List<ComplianceAssessmentEntity> complianceAssessments;

    public EURegulationEntity(EURegulation euRegulation) {
        BeanUtils.copyProperties(euRegulation, this, "complianceAssessments");
    }

    public EURegulation toDomain() {
        EURegulation euRegulation = new EURegulation();
        BeanUtils.copyProperties(this, euRegulation, "complianceAssessments");
        return euRegulation;
    }
}
