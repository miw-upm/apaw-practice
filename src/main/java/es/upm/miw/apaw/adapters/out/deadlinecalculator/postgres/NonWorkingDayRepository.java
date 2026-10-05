package es.upm.miw.apaw.adapters.out.deadlinecalculator.postgres;

import es.upm.miw.apaw.domain.model.deadlinecalculator.ScopeLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface NonWorkingDayRepository extends JpaRepository<NonWorkingDayEntity, UUID> {
    boolean existsByDateAndScopeLevelAndRegionAndCity(
            LocalDate date, ScopeLevel scopeLevel, String region, String city);

    List<NonWorkingDayEntity> findAllByOrderByDateAscDescriptionAsc();
}
