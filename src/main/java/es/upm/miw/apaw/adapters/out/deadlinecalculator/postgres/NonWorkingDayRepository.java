package es.upm.miw.apaw.adapters.out.deadlinecalculator.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NonWorkingDayRepository extends JpaRepository<NonWorkingDayEntity, UUID> {
}
