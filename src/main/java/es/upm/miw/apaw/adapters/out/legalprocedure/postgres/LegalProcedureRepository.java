package es.upm.miw.apaw.adapters.out.legalprocedure.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface LegalProcedureRepository extends JpaRepository<LegalProcedureEntity, UUID>,
        JpaSpecificationExecutor<LegalProcedureEntity> {
    boolean existsByLegalTasksId(UUID id);

    boolean existsByTitle(String title);
}
