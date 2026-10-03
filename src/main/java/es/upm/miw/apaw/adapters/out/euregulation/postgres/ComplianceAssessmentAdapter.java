package es.upm.miw.apaw.adapters.out.euregulation.postgres;

import es.upm.miw.apaw.domain.model.euregulation.ComplianceAssessment;
import es.upm.miw.apaw.domain.ports.out.euregulation.ComplianceAssessmentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ComplianceAssessmentAdapter implements ComplianceAssessmentGateway {

    private final ComplianceAssessmentRepository complianceAssessmentRepository;
    private final EURegulationRepository euRegulationRepository;

    @Override
    public boolean existsById(UUID id) {
        return this.complianceAssessmentRepository.existsById(id);
    }

    @Override
    @Transactional
    public ComplianceAssessment create(ComplianceAssessment complianceAssessment) {
        List<EURegulationEntity> euRegulationEntities = complianceAssessment.getEuRegulations().stream()
                .map(euRegulation -> this.euRegulationRepository.getReferenceById(euRegulation.getId()))
                .toList();
        ComplianceAssessmentEntity entity = new ComplianceAssessmentEntity(complianceAssessment, euRegulationEntities);
        return this.complianceAssessmentRepository.save(entity).toDomain();
    }
}
