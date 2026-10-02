package es.upm.miw.apaw.adapters.out.courthearing.postgres;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;
import es.upm.miw.apaw.domain.model.courthearing.CourtHearingByCourtReport;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface CourtHearingRepository extends JpaRepository<CourtHearingEntity, UUID>,
        JpaSpecificationExecutor<CourtHearingEntity> {

    @Override
    @EntityGraph(attributePaths = "attendeeIds")
    List<CourtHearingEntity> findAll(Specification<CourtHearingEntity> specification, Sort sort);

    boolean existsByCourtId(UUID courtId);

    @Query("""
            select new es.upm.miw.apaw.domain.model.courthearing.CourtHearingByCourtReport(
                court.name,
                count(hearing),
                sum(case when hearing.status = es.upm.miw.apaw.domain.model.courthearing.CourtHearingStatus.SCHEDULED
                    then 1 else 0 end)
            )
            from CourtHearingEntity hearing
            join hearing.court court
            group by court.name
            order by count(hearing) desc, court.name asc
            """)
    List<CourtHearingByCourtReport> findHearingByCourtReport();
}