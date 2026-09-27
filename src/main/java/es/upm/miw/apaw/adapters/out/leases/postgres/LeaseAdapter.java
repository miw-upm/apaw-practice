package es.upm.miw.apaw.adapters.out.leases.postgres;

import es.upm.miw.apaw.domain.ports.out.leases.LeaseGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LeaseAdapter implements LeaseGateway {
    private final LeaseRepository leaseRepository;
}
