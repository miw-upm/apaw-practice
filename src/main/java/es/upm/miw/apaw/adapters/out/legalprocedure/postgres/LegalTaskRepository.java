package es.upm.miw.apaw.adapters.out.legalprocedure.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LegalTaskRepository extends JpaRepository<LegalTaskEntity, UUID> {
    boolean existsByTitle(String title);
}
