package es.upm.miw.apaw.adapters.out.probate.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EstateRepository extends JpaRepository<EstateEntity, UUID> {
    boolean existsByHeirsId(UUID id);

    boolean existsByFileNumber(String fileNumber);
}
