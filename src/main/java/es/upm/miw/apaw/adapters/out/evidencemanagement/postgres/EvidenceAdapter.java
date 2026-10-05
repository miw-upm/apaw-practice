package es.upm.miw.apaw.adapters.out.evidencemanagement.postgres;

import es.upm.miw.apaw.domain.ports.out.evidencemanagement.EvidenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EvidenceAdapter implements EvidenceGateway {

    private final EvidenceRepository evidenceRepository;

}
