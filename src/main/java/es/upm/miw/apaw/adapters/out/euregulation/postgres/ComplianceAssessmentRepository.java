package es.upm.miw.apaw.adapters.out.euregulation.postgres;

import es.upm.miw.apaw.domain.model.euregulation.ComplianceByAreaReport;
import es.upm.miw.apaw.domain.model.euregulation.LawyerProductivityReport;
import es.upm.miw.apaw.domain.model.euregulation.OverdueAssessmentReport;
import es.upm.miw.apaw.domain.model.euregulation.RiskExposureReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ComplianceAssessmentRepository extends JpaRepository<ComplianceAssessmentEntity, UUID> {
    @EntityGraph(attributePaths = "euRegulations")
    List<ComplianceAssessmentEntity> findAllByOrderByAssessmentDateAscIdAsc();

    @Query("""
            select new es.upm.miw.apaw.domain.model.euregulation.ComplianceByAreaReport(
                regulation.applicationArea,
                count(distinct assessment.id),
                count(distinct case when assessment.complianceLevel =
                    es.upm.miw.apaw.domain.model.euregulation.ComplianceLevel.COMPLIANT
                    then assessment.id else null end),
                count(distinct case when assessment.complianceLevel =
                    es.upm.miw.apaw.domain.model.euregulation.ComplianceLevel.PARTIALLY_COMPLIANT
                    then assessment.id else null end),
                count(distinct case when assessment.complianceLevel =
                    es.upm.miw.apaw.domain.model.euregulation.ComplianceLevel.NON_COMPLIANT
                    then assessment.id else null end),
                count(distinct case when assessment.complianceLevel =
                    es.upm.miw.apaw.domain.model.euregulation.ComplianceLevel.PENDING_REVIEW
                    then assessment.id else null end)
            )
            from ComplianceAssessmentEntity assessment
            join assessment.euRegulations regulation
            group by regulation.applicationArea
            order by (1.0 * count(distinct case when assessment.complianceLevel =
                es.upm.miw.apaw.domain.model.euregulation.ComplianceLevel.COMPLIANT
                then assessment.id else null end) / count(distinct assessment.id)) asc,
                regulation.applicationArea asc
            """)
    List<ComplianceByAreaReport> findComplianceByAreaReport();

    @Query("""
            select new es.upm.miw.apaw.domain.model.euregulation.OverdueAssessmentReport(
                assessment.userId,
                count(assessment.id),
                sum(case when assessment.complianceDeadline < current_date then 1L else 0L end),
                sum(case when assessment.complianceDeadline >= current_date
                    and assessment.complianceDeadline <= current_date + 30 day then 1L else 0L end),
                min(assessment.complianceDeadline)
            )
            from ComplianceAssessmentEntity assessment
            where assessment.complianceDeadline is not null
            group by assessment.userId
            order by sum(case when assessment.complianceDeadline < current_date then 1L else 0L end) desc,
                min(assessment.complianceDeadline) asc,
                assessment.userId asc
            """)
    List<OverdueAssessmentReport> findOverdueAssessmentReport();

    @Query("""
            select new es.upm.miw.apaw.domain.model.euregulation.LawyerProductivityReport(
                assessment.responsibleLawyer,
                count(assessment.id),
                sum(case when assessment.aiGenerated = true then 1L else 0L end),
                sum(case when assessment.aiGenerated = false then 1L else 0L end)
            )
            from ComplianceAssessmentEntity assessment
            group by assessment.responsibleLawyer
            order by count(assessment.id) desc, assessment.responsibleLawyer asc
            """)
    List<LawyerProductivityReport> findLawyerProductivityReport();

    @Query("""
            select new es.upm.miw.apaw.domain.model.euregulation.RiskExposureReport(
                assessment.userId,
                count(assessment.id),
                sum(case when assessment.riskLevel =
                    es.upm.miw.apaw.domain.model.euregulation.RiskLevel.HIGH then 1L else 0L end),
                sum(case when assessment.riskLevel =
                    es.upm.miw.apaw.domain.model.euregulation.RiskLevel.MEDIUM then 1L else 0L end),
                sum(case when assessment.riskLevel =
                    es.upm.miw.apaw.domain.model.euregulation.RiskLevel.LOW then 1L else 0L end),
                sum(case when assessment.complianceLevel =
                    es.upm.miw.apaw.domain.model.euregulation.ComplianceLevel.NON_COMPLIANT
                    then 1L else 0L end)
            )
            from ComplianceAssessmentEntity assessment
            group by assessment.userId
            order by (
                3 * sum(case when assessment.riskLevel =
                    es.upm.miw.apaw.domain.model.euregulation.RiskLevel.HIGH then 1L else 0L end)
                + 2 * sum(case when assessment.riskLevel =
                    es.upm.miw.apaw.domain.model.euregulation.RiskLevel.MEDIUM then 1L else 0L end)
                + sum(case when assessment.riskLevel =
                    es.upm.miw.apaw.domain.model.euregulation.RiskLevel.LOW then 1L else 0L end)
            ) desc, assessment.userId asc
            """)
    List<RiskExposureReport> findRiskExposureReport();
}
