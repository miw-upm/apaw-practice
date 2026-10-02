package es.upm.miw.apaw.adapters.out.secondlawchance.postgres;

import es.upm.miw.apaw.domain.ports.out.secondlawchance.ExonerationCaseGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExonerationCaseAdapter implements ExonerationCaseGateway {
    private final ExonerationCaseRepository exonerationCaseRepository;
}
