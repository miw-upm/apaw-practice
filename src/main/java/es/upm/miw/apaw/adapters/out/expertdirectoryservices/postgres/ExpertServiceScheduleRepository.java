package es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ExpertServiceScheduleRepository extends JpaRepository<ExpertServiceScheduleEntity, UUID>,
        JpaSpecificationExecutor<ExpertServiceScheduleEntity> {

    @Override
    @EntityGraph(attributePaths = "legalExpertProfiles")
    List<ExpertServiceScheduleEntity> findAll(Specification<ExpertServiceScheduleEntity> specification, Sort sort);

    boolean existsByTariffCode(String tariffCode);

    boolean existsByLegalExpertProfilesIdIn(Collection<UUID> ids);

    @Query("""
            select new es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres.SpecialtyReportRow(
                profile.specialtyArea,
                count(profile),
                count(distinct schedule),
                avg(schedule.rateAmount),
                avg(profile.yearsOfExperience),
                (select veteran.userId
                    from ExpertServiceScheduleEntity veteranSchedule
                    join veteranSchedule.legalExpertProfiles veteran
                    where veteran.specialtyArea = profile.specialtyArea
                    order by veteran.yearsOfExperience desc, veteran.taxIdCode asc
                    limit 1)
            )
            from ExpertServiceScheduleEntity schedule
            join schedule.legalExpertProfiles profile
            group by profile.specialtyArea
            order by count(distinct schedule) desc, profile.specialtyArea asc
            """)
    List<SpecialtyReportRow> findSpecialtyReportRows();
}