package es.upm.miw.apaw.adapters.out.evidencemanagement.postgres;

import es.upm.miw.apaw.domain.ports.out.evidencemanagement.EvidenceGateway;
import es.upm.miw.apaw.domain.model.evidencemanagement.Evidence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EvidenceAdapter implements EvidenceGateway {

    private final EvidenceRepository evidenceRepository;

    @Override
    public Evidence create(Evidence evidence) {
        return this.evidenceRepository
                .save(new EvidenceEntity(evidence))
                .toDomain();
    }

}
