package es.upm.miw.apaw.domain.ports.out.leases;

import es.upm.miw.apaw.domain.model.leases.Lease;

public interface LeaseGateway {
    Lease create(Lease lease);

    boolean existsByLeaseNumber(String leaseNumber);

    boolean existsByCadastralReference(String cadastralReference);
}
