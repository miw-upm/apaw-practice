package es.upm.miw.apaw.domain.ports.out.euregulation;

import es.upm.miw.apaw.domain.model.euregulation.ComplianceAssessment;
import es.upm.miw.apaw.domain.model.euregulation.ComplianceByAreaReport;

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

    List<ComplianceByAreaReport> findComplianceByAreaReport();
}
