package es.upm.miw.apaw.domain.ports.out.evidencemanagement;

import es.upm.miw.apaw.domain.model.evidencemanagement.Evidence;

public interface EvidenceGateway {
    Evidence create(Evidence evidence);
}
