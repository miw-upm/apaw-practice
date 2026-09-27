package es.upm.miw.apaw.adapters.out.leases.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface LeaseRepository extends JpaRepository<LeaseEntity, UUID>,
        JpaSpecificationExecutor<LeaseEntity> {
    boolean existsByAmendmentsId(UUID id);

    boolean existsByLeaseNumber(String leaseNumber);

    boolean existsByCadastralReference(String cadastralReference);
}
