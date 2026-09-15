package es.upm.miw.apaw.adapters.out.legalprocedure.postgres;

import es.upm.miw.apaw.domain.models.legalprocedure.LegalTask;
import es.upm.miw.apaw.domain.ports.out.legalprocedure.LegalTaskGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class LegalTaskAdapter implements LegalTaskGateway {
    private final LegalTaskRepository legalTaskRepository;
    private final LegalProcedureRepository legalProcedureRepository;

    @Override
    public LegalTask create(LegalTask legalTask) {
        return this.legalTaskRepository
                .save(new LegalTaskEntity(legalTask))
                .toDomain();
    }

    @Override
    public List<LegalTask> findAll() {
        return this.legalTaskRepository.findAllByOrderByTitleAsc().stream()
                .map(LegalTaskEntity::toDomain)
                .toList();
    }

    @Override
    public Optional<LegalTask> read(UUID id) {
        return this.legalTaskRepository.findById(id)
                .map(LegalTaskEntity::toDomain);
    }

    @Override
    public LegalTask update(LegalTask legalTask) {
        return this.legalTaskRepository
                .save(new LegalTaskEntity(legalTask))
                .toDomain();
    }

    @Override
    public void delete(UUID id) {
        this.legalTaskRepository.deleteById(id);
    }

    @Override
    public boolean isReferenced(UUID id) {
        return this.legalProcedureRepository.existsByLegalTasksId(id);
    }

    @Override
    public boolean existsByTitle(String title) {
        return this.legalTaskRepository.existsByTitle(title);
    }
}
