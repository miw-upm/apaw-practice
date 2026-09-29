package es.upm.miw.apaw.adapters.out.contract.postgres;

import es.upm.miw.apaw.domain.model.contract.Clause;
import es.upm.miw.apaw.domain.ports.out.contract.ClauseGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ClauseAdapter implements ClauseGateway {
    private final ClauseRepository clauseRepository;

    @Override
    public Clause create(Clause clause) {
        return this.clauseRepository
                .save(new ClauseEntity(clause))
                .toDomain();
    }

    @Override
    public Optional<Clause> read(UUID id) {
        return this.clauseRepository.findById(id)
                .map(ClauseEntity::toDomain);
    }
}