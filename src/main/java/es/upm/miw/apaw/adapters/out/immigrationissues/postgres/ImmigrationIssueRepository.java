package es.upm.miw.apaw.adapters.out.immigrationissues.postgres;

import es.upm.miw.apaw.domain.model.immigrationissues.LawBasisUsageReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ImmigrationIssueRepository extends JpaRepository<ImmigrationIssueEntity, UUID> {

    boolean existsBySubject(String subject);

    boolean existsByLawBases_Id(UUID id);

    @Query("""
            select new es.upm.miw.apaw.domain.model.immigrationissues.LawBasisUsageReport(
                issue.clientImmigrationStatus,
                lawBasis.lawCode,
                count(distinct issue)
            )
            from ImmigrationIssueEntity issue
            join issue.lawBases lawBasis
            group by issue.clientImmigrationStatus, lawBasis.lawCode
            order by count(distinct issue) desc, issue.clientImmigrationStatus, lawBasis.lawCode
            """)
    List<LawBasisUsageReport> findLawBasisUsageReport();
}