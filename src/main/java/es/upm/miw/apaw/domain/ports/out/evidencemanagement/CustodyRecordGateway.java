package es.upm.miw.apaw.domain.ports.out.evidencemanagement;

import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;

public interface CustodyRecordGateway {
    CustodyRecord create(CustodyRecord custodyRecord);
}
