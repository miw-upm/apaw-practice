package es.upm.miw.apaw.adapters.out.judicialcourt.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JudicialCourtTypeRepository extends JpaRepository<JudicialCourtTypeEntity, UUID> {
    boolean existsByName(String name);

    boolean existsByCode(String code);
}
