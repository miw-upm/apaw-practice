package es.upm.miw.apaw.domain.ports.out.evidencemanagement;

import java.util.UUID;

public interface EvidenceGateway {
    boolean existsByCustodyRecordId(UUID custodyRecordId);
}
