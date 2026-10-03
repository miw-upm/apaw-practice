package es.upm.miw.apaw.domain.services.evidencemanagement;

import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import es.upm.miw.apaw.domain.ports.out.evidencemanagement.CustodyRecordGateway;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustodyRecordService {
    private final CustodyRecordGateway custodyRecordGateway;

    public CustodyRecord create(CustodyRecord custodyRecord) {
        custodyRecord.doDefault();
        return this.custodyRecordGateway.create(custodyRecord);
    }

    public CustodyRecord read(UUID id) {
        return this.custodyRecordGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Custody record id not found: " + id));
    }
}
