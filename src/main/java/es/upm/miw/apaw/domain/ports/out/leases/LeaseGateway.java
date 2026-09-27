package es.upm.miw.apaw.domain.ports.out.leases;

import es.upm.miw.apaw.domain.model.leases.Lease;
import es.upm.miw.apaw.domain.model.leases.LeaseFindCriteria;

import java.util.List;

public interface LeaseGateway {
    Lease create(Lease lease);

    List<Lease> find(LeaseFindCriteria criteria);

    boolean existsByLeaseNumber(String leaseNumber);

    boolean existsByCadastralReference(String cadastralReference);
}
