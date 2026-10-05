package es.upm.miw.apaw.adapters.out.stucktaskdetector.postgres;

import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskRuleAlertReport;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface StuckTaskAlertRepository extends JpaRepository<StuckTaskAlertEntity, UUID> {
    boolean existsByReference(String reference);

    @EntityGraph(attributePaths = "stuckTaskRule")
    List<StuckTaskAlertEntity> findAllByOrderByDetectedAtAscIdAsc();

    @Query("""
        select new es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskRuleAlertReport(
            rule.name,
            rule.createdByUserId,
            count(alert),
            sum(case when alert.resolvedAt is null then 1 else 0 end)
        )
        from StuckTaskAlertEntity alert
        join alert.stuckTaskRule rule
        group by rule.name, rule.createdByUserId
        order by count(alert) desc, rule.name asc
        """)
    List<StuckTaskRuleAlertReport> findStuckTaskRuleAlertReport();

}
