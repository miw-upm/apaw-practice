package es.upm.miw.apaw.adapters.out.deadlinecalculator.postgres;

import es.upm.miw.apaw.domain.model.deadlinecalculator.ScopeLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface NonWorkingDayRepository extends JpaRepository<NonWorkingDayEntity, UUID> {
    boolean existsByDateAndScopeLevelAndRegionAndCity(
            LocalDate date, ScopeLevel scopeLevel, String region, String city);

    List<NonWorkingDayEntity> findAllByOrderByDateAscDescriptionAscIdAsc();

    boolean existsByDateAndScopeLevelAndRegionAndCityAndIdNot(
            LocalDate date, ScopeLevel scopeLevel, String region, String city, UUID id);

    @Query("""
            select nonWorkingDay from NonWorkingDayEntity nonWorkingDay
            where nonWorkingDay.scopeLevel = es.upm.miw.apaw.domain.model.deadlinecalculator.ScopeLevel.NATIONAL
               or (nonWorkingDay.scopeLevel = es.upm.miw.apaw.domain.model.deadlinecalculator.ScopeLevel.REGIONAL
                   and nonWorkingDay.region = :region)
               or (nonWorkingDay.scopeLevel = es.upm.miw.apaw.domain.model.deadlinecalculator.ScopeLevel.LOCAL
                   and nonWorkingDay.region = :region and nonWorkingDay.city = :city)
            order by nonWorkingDay.date asc, nonWorkingDay.id asc
            """)
    List<NonWorkingDayEntity> findApplicable(@Param("region") String region, @Param("city") String city);
}
