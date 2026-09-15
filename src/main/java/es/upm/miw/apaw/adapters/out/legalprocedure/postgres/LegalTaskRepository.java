package es.upm.miw.apaw.adapters.out.legalprocedure.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LegalTaskRepository extends JpaRepository<LegalTaskEntity, UUID> {
    List<LegalTaskEntity> findAllByOrderByTitleAscIdAsc();

    boolean existsByTitle(String title);
}
