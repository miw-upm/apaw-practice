package es.upm.miw.apaw.adapters.out.legalprocedure.postgres;

import es.upm.miw.apaw.domain.models.legalprocedure.LegalProcedure;
import es.upm.miw.apaw.domain.ports.out.legalprocedure.LegalProcedureGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LegalProcedureAdapter implements LegalProcedureGateway {
    private final LegalProcedureRepository legalProcedureRepository;
    private final LegalTaskRepository legalTaskRepository;

    @Override
    @Transactional
    public LegalProcedure create(LegalProcedure legalProcedure) {
        LegalProcedureEntity legalProcedureEntity = new LegalProcedureEntity(legalProcedure);
        List<LegalTaskEntity> legalTaskEntities = legalProcedure.getLegalTasks().stream()
                .map(legalTask -> this.legalTaskRepository.getReferenceById(legalTask.getId()))
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
        legalProcedureEntity.setLegalTasks(legalTaskEntities);
        this.legalProcedureRepository.save(legalProcedureEntity);
        return legalProcedure;
    }

    @Override
    public boolean existsByTitle(String title) {
        return this.legalProcedureRepository.existsByTitle(title);
    }
}
