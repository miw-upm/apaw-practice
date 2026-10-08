package es.upm.miw.apaw.domain.ports.out.evidencemanagement;

import es.upm.miw.apaw.domain.model.evidencemanagement.Evidence;
import es.upm.miw.apaw.domain.model.evidencemanagement.EvidenceFindCriteria;

import java.util.List;

public interface EvidenceGateway {
    Evidence create(Evidence evidence);

    List<Evidence> find(EvidenceFindCriteria criteria);
}
