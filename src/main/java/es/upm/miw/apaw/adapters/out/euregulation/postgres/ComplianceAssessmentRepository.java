package es.upm.miw.apaw.adapters.out.euregulation.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;
import java.util.UUID;

public interface ComplianceAssessmentRepository extends JpaRepository<ComplianceAssessmentEntity, UUID> {
    @EntityGraph(attributePaths = "euRegulations")
    List<ComplianceAssessmentEntity> findAllByOrderByAssessmentDateAscIdAsc();
}
