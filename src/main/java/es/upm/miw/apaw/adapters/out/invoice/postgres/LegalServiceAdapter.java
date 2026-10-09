package es.upm.miw.apaw.adapters.out.invoice.postgres;

import es.upm.miw.apaw.domain.model.invoice.LegalService;
import es.upm.miw.apaw.domain.ports.out.invoice.LegalServiceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LegalServiceAdapter implements LegalServiceGateway {

    private final LegalServiceRepository legalServiceRepository;

    @Override
    public LegalService create(LegalService legalService) {
        return this.legalServiceRepository
                .save(new LegalServiceEntity(legalService))
                .toDomain();
    }

    @Override
    public boolean existsByName(String name) {
        return this.legalServiceRepository.existsByName(name);
    }
}