package es.upm.miw.apaw.domain.services.evidencemanagement;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.evidencemanagement.CreationEvidence;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import es.upm.miw.apaw.domain.model.evidencemanagement.Evidence;
import es.upm.miw.apaw.domain.ports.out.evidencemanagement.CustodyRecordGateway;
import es.upm.miw.apaw.domain.ports.out.evidencemanagement.EvidenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EvidenceService {
    private final EvidenceGateway evidenceGateway;
    private final CustodyRecordGateway custodyRecordGateway;

    public Evidence create(CreationEvidence creation) {
        Evidence evidence = new Evidence();
        BeanUtils.copyProperties(creation, evidence);
        evidence.setCustodyRecords(creation.getCustodyRecordIds().stream()
                .map(this::readFreeCustodyRecord)
                .toList());
        evidence.doDefault();
        return this.evidenceGateway.create(evidence);
    }

    private CustodyRecord readFreeCustodyRecord(UUID id) {
        CustodyRecord custodyRecord = this.custodyRecordGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Custody record id not found: " + id));
        if (this.custodyRecordGateway.isReferenced(id)) {
            throw new ConflictException("Custody record is already associated with an evidence: " + id);
        }
        return custodyRecord;
    }
}