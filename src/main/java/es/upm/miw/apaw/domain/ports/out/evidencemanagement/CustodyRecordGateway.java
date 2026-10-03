package es.upm.miw.apaw.domain.ports.out.evidencemanagement;

import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import java.util.UUID;

public interface CustodyRecordGateway {
    CustodyRecord create(CustodyRecord custodyRecord);
    CustodyRecord readById(UUID id);
}
