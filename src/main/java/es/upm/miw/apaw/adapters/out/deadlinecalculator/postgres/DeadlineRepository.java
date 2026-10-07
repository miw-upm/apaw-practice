package es.upm.miw.apaw.adapters.out.deadlinecalculator.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeadlineRepository extends JpaRepository<DeadlineEntity, UUID> {
    boolean existsByNonWorkingDaysId(UUID id);

    boolean existsByTitle(String title);
}
