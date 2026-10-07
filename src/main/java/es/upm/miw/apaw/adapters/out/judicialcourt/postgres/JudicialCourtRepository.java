package es.upm.miw.apaw.adapters.out.judicialcourt.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JudicialCourtRepository extends JpaRepository<JudicialCourtEntity, UUID> {
    boolean existsByTypeId(UUID id);
}
