package es.upm.miw.apaw.adapters.out.carreservation.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<CarEntity, UUID> {
}