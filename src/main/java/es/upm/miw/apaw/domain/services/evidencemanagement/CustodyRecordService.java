package es.upm.miw.apaw.domain.services.evidencemanagement;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import es.upm.miw.apaw.domain.ports.out.evidencemanagement.CustodyRecordGateway;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.ports.out.evidencemanagement.EvidenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustodyRecordService {
    private final CustodyRecordGateway custodyRecordGateway;
    private final EvidenceGateway evidenceGateway;

    public CustodyRecord create(CustodyRecord custodyRecord) {
        custodyRecord.doDefault();
        return this.custodyRecordGateway.create(custodyRecord);
    }

    public CustodyRecord read(UUID id) {
        return this.custodyRecordGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Custody record id not found: " + id));
    }

    public CustodyRecord update(UUID id, CustodyRecord custodyRecord) {
        CustodyRecord existing = this.read(id);
        existing.setDurationMinutes(custodyRecord.getDurationMinutes());
        existing.setAction(custodyRecord.getAction());
        existing.setLocation(custodyRecord.getLocation());
        existing.setNotes(custodyRecord.getNotes());
        existing.setCustodian(custodyRecord.getCustodian());
        return this.custodyRecordGateway.update(existing);
    }

    public void delete(UUID id) {
        if (this.evidenceGateway.existsByCustodyRecordId(id)) {
            throw new ConflictException("Custody record is used by an evidence: " + id);
        }
        this.custodyRecordGateway.deleteById(id);
    }
}
