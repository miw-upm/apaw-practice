package es.upm.miw.apaw.adapters.out.euregulation.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.euregulation.ComplianceAssessment;
import es.upm.miw.apaw.domain.model.euregulation.ComplianceLevel;
import es.upm.miw.apaw.domain.model.euregulation.RiskLevel;
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
public class ComplianceAssessmentEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private String responsibleLawyer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComplianceLevel complianceLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RiskLevel riskLevel;

    @Column(nullable = false)
    private LocalDate assessmentDate;

    private LocalDate complianceDeadline;

    private LocalDate nextReviewDate;

    @Column(columnDefinition = "TEXT")
    private String correctiveActions;

    private String supportingDocumentation;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(nullable = false)
    private Boolean aiGenerated;

    @Column(nullable = false)
    private UUID userId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "compliance_assessment_eu_regulations",
            joinColumns = @JoinColumn(name = "compliance_assessment_id"),
            inverseJoinColumns = @JoinColumn(name = "eu_regulation_id")
    )
    private List<EURegulationEntity> euRegulations;

    public ComplianceAssessmentEntity(
            ComplianceAssessment complianceAssessment, List<EURegulationEntity> euRegulations) {
        BeanUtils.copyProperties(complianceAssessment, this, "userSnapshot", "euRegulations");
        this.userId = complianceAssessment.getUserSnapshot().getId();
        this.euRegulations = euRegulations;
    }

    public ComplianceAssessment toDomain() {
        ComplianceAssessment complianceAssessment = new ComplianceAssessment();
        BeanUtils.copyProperties(this, complianceAssessment, "userId", "euRegulations");
        complianceAssessment.setUserSnapshot(UserSnapshot.builder().id(this.userId).build());
        complianceAssessment.setEuRegulations(this.euRegulations.stream()
                .map(EURegulationEntity::toDomain)
                .toList());
        return complianceAssessment;
    }
}
