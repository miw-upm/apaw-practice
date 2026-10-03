package es.upm.miw.apaw.domain.ports.out.euregulation;

import es.upm.miw.apaw.domain.model.euregulation.ComplianceAssessment;

import java.util.Optional;
import java.util.UUID;

public interface ComplianceAssessmentGateway {
    boolean existsById(UUID id);

    ComplianceAssessment create(ComplianceAssessment complianceAssessment);

    Optional<ComplianceAssessment> read(UUID id);

    ComplianceAssessment update(ComplianceAssessment complianceAssessment);
}
