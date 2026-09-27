package es.upm.miw.apaw.adapters.out.leases.postgres;

import es.upm.miw.apaw.domain.ports.out.leases.AmendmentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AmendmentAdapter implements AmendmentGateway {
    private final AmendmentRepository amendmentRepository;
}
