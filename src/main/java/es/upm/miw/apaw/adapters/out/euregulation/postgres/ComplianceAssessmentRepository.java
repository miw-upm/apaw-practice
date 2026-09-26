package es.upm.miw.apaw.adapters.out.euregulation.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ComplianceAssessmentRepository extends JpaRepository<ComplianceAssessmentEntity, UUID> {
}
