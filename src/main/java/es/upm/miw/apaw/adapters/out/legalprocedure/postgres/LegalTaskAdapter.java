package es.upm.miw.apaw.adapters.out.legalprocedure.postgres;

import es.upm.miw.apaw.domain.models.legalprocedure.LegalTask;
import es.upm.miw.apaw.domain.ports.out.legalprocedure.LegalTaskGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LegalTaskAdapter implements LegalTaskGateway {
    private final LegalTaskRepository legalTaskRepository;

    @Override
    public LegalTask create(LegalTask legalTask) {
        return this.legalTaskRepository
                .save(new LegalTaskEntity(legalTask))
                .toDomain();
    }

    @Override
    public boolean existsByTitle(String title) {
        return this.legalTaskRepository.existsByTitle(title);
    }
}
