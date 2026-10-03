package es.upm.miw.apaw.domain.services.evidencemanagement;

import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import es.upm.miw.apaw.domain.ports.out.evidencemanagement.CustodyRecordGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustodyRecordService {
    private final CustodyRecordGateway custodyRecordGateway;

    public CustodyRecord create(CustodyRecord custodyRecord) {
        custodyRecord.doDefault();
        return this.custodyRecordGateway.create(custodyRecord);
    }
}
