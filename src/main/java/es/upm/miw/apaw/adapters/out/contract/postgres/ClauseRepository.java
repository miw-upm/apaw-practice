package es.upm.miw.apaw.adapters.out.contract.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClauseRepository extends JpaRepository<ClauseEntity, UUID> {
    List<ClauseEntity> findAllByOrderByTitleAscIdAsc();
}