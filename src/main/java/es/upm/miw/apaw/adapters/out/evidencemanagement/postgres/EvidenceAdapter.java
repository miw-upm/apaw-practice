package es.upm.miw.apaw.adapters.out.evidencemanagement.postgres;

import es.upm.miw.apaw.domain.ports.out.evidencemanagement.EvidenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EvidenceAdapter implements EvidenceGateway {

    private final EvidenceRepository evidenceRepository;

    @Override
    public boolean existsByCustodyRecordId(UUID custodyRecordId) {
        return this.evidenceRepository.existsByCustodyRecordsId(custodyRecordId);
    }
}
