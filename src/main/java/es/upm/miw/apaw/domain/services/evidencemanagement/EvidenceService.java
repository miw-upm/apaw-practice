package es.upm.miw.apaw.domain.services.evidencemanagement;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.evidencemanagement.CreationEvidence;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import es.upm.miw.apaw.domain.model.evidencemanagement.Evidence;
import es.upm.miw.apaw.domain.model.evidencemanagement.EvidenceFindCriteria;
import es.upm.miw.apaw.domain.ports.out.evidencemanagement.CustodyRecordGateway;
import es.upm.miw.apaw.domain.ports.out.evidencemanagement.EvidenceGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EvidenceService {
    private final EvidenceGateway evidenceGateway;
    private final CustodyRecordGateway custodyRecordGateway;
    private final UserFinder userFinder;

    public Evidence create(CreationEvidence creation) {
        this.assertNoRepeatedCustodyRecordIds(creation.getCustodyRecordIds());
        Evidence evidence = new Evidence();
        BeanUtils.copyProperties(creation, evidence);
        evidence.setCustodyRecords(creation.getCustodyRecordIds().stream()
                .map(this::readFreeCustodyRecord)
                .toList());
        evidence.doDefault();
        return this.evidenceGateway.create(evidence);
    }

    public List<Evidence> find(EvidenceFindCriteria criteria) {
        List<Evidence> evidences = this.evidenceGateway.find(criteria);
        Set<UUID> custodianIds = evidences.stream()
                .flatMap(evidence -> evidence.getCustodyRecords().stream())
                .map(custodyRecord -> custodyRecord.getCustodian().getId())
                .collect(Collectors.toSet());
        if (custodianIds.isEmpty()) {
            return criteria.hasCustodianFirstName() ? List.of() : evidences;
        }
        Map<UUID, UserSnapshot> custodiansById = this.userFinder.findByIds(custodianIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        evidences.forEach(evidence -> evidence.getCustodyRecords().forEach(
                custodyRecord -> custodyRecord.setCustodian(this.readCustodian(custodiansById, custodyRecord))));
        return evidences.stream()
                .filter(evidence -> this.matchesCustodianFirstName(criteria, evidence))
                .toList();
    }

    private UserSnapshot readCustodian(Map<UUID, UserSnapshot> custodiansById, CustodyRecord custodyRecord) {
        UUID custodianId = custodyRecord.getCustodian().getId();
        UserSnapshot custodian = custodiansById.get(custodianId);
        if (custodian == null) {
            throw new NotFoundException("User id not found: " + custodianId);
        }
        return custodian;
    }

    private boolean matchesCustodianFirstName(EvidenceFindCriteria criteria, Evidence evidence) {
        return !criteria.hasCustodianFirstName() || evidence.getCustodyRecords().stream()
                .anyMatch(custodyRecord -> criteria.getCustodianFirstName().trim()
                        .equalsIgnoreCase(custodyRecord.getCustodian().getFirstName()));
    }

    private CustodyRecord readFreeCustodyRecord(UUID id) {
        CustodyRecord custodyRecord = this.custodyRecordGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Custody record id not found: " + id));
        if (this.custodyRecordGateway.isReferenced(id)) {
            throw new ConflictException("Custody record is already associated with an evidence: " + id);
        }
        return custodyRecord;
    }

    private void assertNoRepeatedCustodyRecordIds(List<UUID> ids) {
        Set<UUID> uniqueIds = new HashSet<>();
        for (UUID id : ids) {
            if (!uniqueIds.add(id)) {
                throw new BadRequestException("Repeated custody record id: " + id);
            }
        }
    }
}