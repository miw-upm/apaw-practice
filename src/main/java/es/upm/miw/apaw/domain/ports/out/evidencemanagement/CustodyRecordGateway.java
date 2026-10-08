package es.upm.miw.apaw.domain.ports.out.evidencemanagement;

import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodianActivityReport;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustodyRecordGateway {
    CustodyRecord create(CustodyRecord custodyRecord);

    Optional <CustodyRecord> read(UUID id);

    CustodyRecord update(CustodyRecord custodyRecord);

    boolean isReferenced(UUID id);

    void deleteById(UUID id);

    List<CustodyRecord> findAll();

    List<CustodianActivityReport> findActivityReport();
}
