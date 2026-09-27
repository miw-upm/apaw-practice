package es.upm.miw.apaw.adapters.out.leases.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LeaseRepository extends JpaRepository<LeaseEntity, UUID> {
    boolean existsByAmendmentsId(UUID id);

    boolean existsByLeaseNumber(String leaseNumber);

    boolean existsByCadastralReference(String cadastralReference);
}
