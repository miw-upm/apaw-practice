package es.upm.miw.apaw.adapters.out.deadlinecalculator.postgres;

import es.upm.miw.apaw.domain.model.deadlinecalculator.DeadlineWorkloadReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface DeadlineRepository extends JpaRepository<DeadlineEntity, UUID>,
        JpaSpecificationExecutor<DeadlineEntity> {
    boolean existsByNonWorkingDaysId(UUID id);

    boolean existsByTitle(String title);

    @Query("""
            select new es.upm.miw.apaw.domain.model.deadlinecalculator.DeadlineWorkloadReport(
                deadline.userId,
                count(distinct case when deadline.dueDate < :today then deadline.id else null end),
                count(distinct deadline.id),
                count(distinct case when nonWorkingDay.id is not null then deadline.id else null end)
            )
            from DeadlineEntity deadline
            left join deadline.nonWorkingDays nonWorkingDay
            group by deadline.userId
            order by count(distinct case when deadline.dueDate < :today then deadline.id else null end) desc,
                count(distinct deadline.id) desc,
                deadline.userId asc
            """)
    List<DeadlineWorkloadReport> findWorkloadReport(@Param("today") LocalDate today);
}
