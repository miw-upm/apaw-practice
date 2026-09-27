package es.upm.miw.apaw.domain.ports.out.leases;

import es.upm.miw.apaw.domain.model.leases.Lease;
import es.upm.miw.apaw.domain.model.leases.LeaseAmendmentReport;
import es.upm.miw.apaw.domain.model.leases.LeaseFindCriteria;

import java.util.List;

public interface LeaseGateway {
    Lease create(Lease lease);

    List<Lease> find(LeaseFindCriteria criteria);

    List<LeaseAmendmentReport> findAmendmentReport();

    boolean existsByLeaseNumber(String leaseNumber);

    boolean existsByCadastralReference(String cadastralReference);
}
