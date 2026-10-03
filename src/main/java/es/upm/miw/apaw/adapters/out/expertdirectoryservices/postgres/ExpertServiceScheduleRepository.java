package es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ExpertServiceScheduleRepository extends JpaRepository<ExpertServiceScheduleEntity, UUID> {

    boolean existsByTariffCode(String tariffCode);

    boolean existsByLegalExpertProfilesIdIn(Collection<UUID> ids);

    @Query("""
            select new es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres.SpecialtyReportRow(
                profile.specialtyArea,
                count(profile),
                count(distinct schedule),
                avg(schedule.rateAmount),
                avg(profile.yearsOfExperience)
            )
            from ExpertServiceScheduleEntity schedule
            join schedule.legalExpertProfiles profile
            group by profile.specialtyArea
            order by count(distinct schedule) desc, profile.specialtyArea asc
            """)
    List<SpecialtyReportRow> findSpecialtyReportRows();

    @Query("""
            select new es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres.SpecialtyVeteranRow(
                profile.specialtyArea,
                profile.userId
            )
            from ExpertServiceScheduleEntity schedule
            join schedule.legalExpertProfiles profile
            order by profile.specialtyArea asc, profile.yearsOfExperience desc, profile.taxIdCode asc
            """)
    List<SpecialtyVeteranRow> findSpecialtyVeteranRows();
}