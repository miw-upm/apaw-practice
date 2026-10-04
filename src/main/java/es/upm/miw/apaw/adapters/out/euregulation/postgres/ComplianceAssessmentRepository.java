package es.upm.miw.apaw.adapters.out.euregulation.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ComplianceAssessmentRepository extends JpaRepository<ComplianceAssessmentEntity, UUID> {
    @EntityGraph(attributePaths = "euRegulations")
    List<ComplianceAssessmentEntity> findAllByOrderByAssessmentDateAscIdAsc();

    @Query(value = """
            SELECT er.application_area AS "applicationArea",
                   COUNT(DISTINCT ca.id) AS "totalAssessments",
                   COUNT(DISTINCT ca.id) FILTER (WHERE ca.compliance_level = 'COMPLIANT') AS "compliantCount",
                   COUNT(DISTINCT ca.id) FILTER (WHERE ca.compliance_level = 'PARTIALLY_COMPLIANT')
                       AS "partiallyCompliantCount",
                   COUNT(DISTINCT ca.id) FILTER (WHERE ca.compliance_level = 'NON_COMPLIANT')
                       AS "nonCompliantCount",
                   COUNT(DISTINCT ca.id) FILTER (WHERE ca.compliance_level = 'PENDING_REVIEW')
                       AS "pendingReviewCount",
                   (COUNT(DISTINCT ca.id) FILTER (WHERE ca.compliance_level = 'COMPLIANT'))::numeric
                       / NULLIF(COUNT(DISTINCT ca.id), 0)::numeric AS "complianceRate"
            FROM compliance_assessment_entity ca
            JOIN compliance_assessment_eu_regulations caer
                 ON caer.compliance_assessment_id = ca.id
            JOIN eu_regulation_entity er
                 ON er.id = caer.eu_regulation_id
            GROUP BY er.application_area
            ORDER BY "complianceRate" ASC, er.application_area ASC
            """, nativeQuery = true)
    List<ComplianceByAreaReportProjection> findComplianceByAreaReport();
}
