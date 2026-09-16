package es.upm.miw.apaw.adapters.out.legalprocedure.postgres;

import es.upm.miw.apaw.domain.models.legalprocedure.LegalTaskUsageReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface LegalProcedureRepository extends JpaRepository<LegalProcedureEntity, UUID>,
        JpaSpecificationExecutor<LegalProcedureEntity> {
    boolean existsByLegalTasksId(UUID id);

    boolean existsByTitle(String title);

    @Query("""
            select new es.upm.miw.apaw.domain.models.legalprocedure.LegalTaskUsageReport(
                task.title,
                count(procedure),
                sum(case when procedure.closingDate is null then 1 else 0 end)
            )
            from LegalProcedureEntity procedure
            join procedure.legalTasks task
            group by task.title
            order by count(procedure) desc
            """)
    List<LegalTaskUsageReport> findLegalTaskUsageReport();
}
