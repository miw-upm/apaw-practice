package es.upm.miw.apaw.adapters.out.leases.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AmendmentRepository extends JpaRepository<AmendmentEntity, UUID> {
    List<AmendmentEntity> findAllByOrderByEffectiveDateAscAmendmentNumberAscIdAsc();
}
