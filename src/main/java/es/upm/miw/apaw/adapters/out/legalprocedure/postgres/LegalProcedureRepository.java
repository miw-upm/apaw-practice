package es.upm.miw.apaw.adapters.out.legalprocedure.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LegalProcedureRepository extends JpaRepository<LegalProcedureEntity, UUID> {
    boolean existsByLegalTasksId(UUID id);

    boolean existsByTitle(String title);
}
