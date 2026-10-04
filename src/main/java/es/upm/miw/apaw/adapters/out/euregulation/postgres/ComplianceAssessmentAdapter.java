package es.upm.miw.apaw.adapters.out.euregulation.postgres;

import es.upm.miw.apaw.domain.model.euregulation.ComplianceAssessment;
import es.upm.miw.apaw.domain.model.euregulation.ComplianceByAreaReport;
import es.upm.miw.apaw.domain.ports.out.euregulation.ComplianceAssessmentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    @Override
    @Transactional(readOnly = true)
    public Optional<ComplianceAssessment> read(UUID id) {
        return this.complianceAssessmentRepository.findById(id)
                .map(ComplianceAssessmentEntity::toDomain);
    }

    @Override
    @Transactional
    public ComplianceAssessment update(ComplianceAssessment complianceAssessment) {
        List<EURegulationEntity> euRegulationEntities = complianceAssessment.getEuRegulations().stream()
                .map(euRegulation -> this.euRegulationRepository.getReferenceById(euRegulation.getId()))
                .toList();
        ComplianceAssessmentEntity entity = new ComplianceAssessmentEntity(complianceAssessment, euRegulationEntities);
        return this.complianceAssessmentRepository.save(entity).toDomain();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isReferenced(UUID id) {
        return this.euRegulationRepository.existsByComplianceAssessments_Id(id);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        this.complianceAssessmentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComplianceAssessment> findAll() {
        return this.complianceAssessmentRepository.findAllByOrderByAssessmentDateAscIdAsc().stream()
                .map(ComplianceAssessmentEntity::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComplianceByAreaReport> findComplianceByAreaReport() {
        return this.complianceAssessmentRepository.findComplianceByAreaReport().stream()
                .map(this::toComplianceByAreaReport)
                .toList();
    }

    private ComplianceByAreaReport toComplianceByAreaReport(ComplianceByAreaReportProjection projection) {
        return ComplianceByAreaReport.builder()
                .applicationArea(projection.getApplicationArea())
                .totalAssessments(projection.getTotalAssessments())
                .compliantCount(projection.getCompliantCount())
                .partiallyCompliantCount(projection.getPartiallyCompliantCount())
                .nonCompliantCount(projection.getNonCompliantCount())
                .pendingReviewCount(projection.getPendingReviewCount())
                .complianceRate(projection.getComplianceRate())
                .build();
    }
}
