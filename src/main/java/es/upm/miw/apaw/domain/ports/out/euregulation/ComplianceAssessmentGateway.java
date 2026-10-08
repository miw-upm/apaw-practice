package es.upm.miw.apaw.domain.ports.out.euregulation;

import es.upm.miw.apaw.domain.model.euregulation.ComplianceAssessment;
import es.upm.miw.apaw.domain.model.euregulation.ComplianceAssessmentFindCriteria;
import es.upm.miw.apaw.domain.model.euregulation.ComplianceByAreaReport;
import es.upm.miw.apaw.domain.model.euregulation.OverdueAssessmentReport;
import es.upm.miw.apaw.domain.model.euregulation.LawyerProductivityReport;
import es.upm.miw.apaw.domain.model.euregulation.RiskExposureReport;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ComplianceAssessmentGateway {
    boolean existsById(UUID id);

    ComplianceAssessment create(ComplianceAssessment complianceAssessment);

    Optional<ComplianceAssessment> read(UUID id);

    ComplianceAssessment update(ComplianceAssessment complianceAssessment);

    boolean isReferenced(UUID id);

    void delete(UUID id);

    List<ComplianceAssessment> findAll();

    List<ComplianceAssessment> find(ComplianceAssessmentFindCriteria criteria);

    List<ComplianceByAreaReport> findComplianceByAreaReport();

    List<OverdueAssessmentReport> findOverdueAssessmentReport();

    List<LawyerProductivityReport> findLawyerProductivityReport();

    List<RiskExposureReport> findRiskExposureReport();
}
