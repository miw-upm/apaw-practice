package es.upm.miw.apaw.adapters.out.secondlawchance.postgres;

import es.upm.miw.apaw.domain.ports.out.secondlawchance.DebtGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DebtAdapter implements DebtGateway {
    private final DebtRepository debtRepository;
}
