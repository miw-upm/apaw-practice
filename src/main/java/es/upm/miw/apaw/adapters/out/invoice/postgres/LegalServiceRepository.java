package es.upm.miw.apaw.adapters.out.invoice.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface LegalServiceRepository extends JpaRepository<LegalServiceEntity, UUID> {

    List<LegalServiceEntity> findAllByOrderByNameAscIdAsc();

    boolean existsByName(String name);
}