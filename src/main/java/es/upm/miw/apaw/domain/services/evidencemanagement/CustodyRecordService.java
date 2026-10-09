package es.upm.miw.apaw.domain.services.evidencemanagement;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodianActivityReport;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import es.upm.miw.apaw.domain.ports.out.evidencemanagement.CustodyRecordGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustodyRecordService {
    private final CustodyRecordGateway custodyRecordGateway;
    private final UserFinder userFinder;

    public CustodyRecord create(CustodyRecord custodyRecord) {
        UserSnapshot custodian = this.resolveCustodian(custodyRecord.getCustodian());
        custodyRecord.doDefault();
        return this.withCustodian(this.custodyRecordGateway.create(custodyRecord), custodian);
    }

    public CustodyRecord read(UUID id) {
        CustodyRecord custodyRecord = this.readStored(id);
        UserSnapshot custodian = this.resolveCustodian(custodyRecord.getCustodian());
        return this.withCustodian(custodyRecord, custodian).ofSummary();
    }

    public CustodyRecord update(UUID id, CustodyRecord custodyRecord) {
        CustodyRecord existing = this.readStored(id);
        UserSnapshot custodian = this.resolveCustodian(custodyRecord.getCustodian());
        existing.setDurationMinutes(custodyRecord.getDurationMinutes());
        existing.setAction(custodyRecord.getAction());
        existing.setLocation(custodyRecord.getLocation());
        existing.setNotes(custodyRecord.getNotes());
        existing.setCustodian(custodian);
        return this.withCustodian(this.custodyRecordGateway.update(existing), custodian);
    }

    public void delete(UUID id) {
        if (this.custodyRecordGateway.isReferenced(id)) {
            throw new ConflictException("Custody record is referenced by an evidence: " + id);
        }
        this.custodyRecordGateway.deleteById(id);
    }

    public List<CustodyRecord> findAll() {
        List<CustodyRecord> custodyRecords = this.custodyRecordGateway.findAll();
        if (custodyRecords.isEmpty()) {
            return custodyRecords;
        }
        Map<UUID, UserSnapshot> custodiansById = this.findCustodiansById(custodyRecords.stream()
                .map(custodyRecord -> custodyRecord.getCustodian().getId())
                .collect(Collectors.toSet()));
        return custodyRecords.stream()
                .map(custodyRecord -> this.toSummary(custodyRecord, custodiansById))
                .toList();
    }

    public CustodyRecord patch(UUID id, CustodyRecord custodyRecord) {
        CustodyRecord existing = this.readStored(id);
        if (custodyRecord.getDurationMinutes() != null) {
            existing.setDurationMinutes(custodyRecord.getDurationMinutes());
        }
        if (custodyRecord.getAction() != null) {
            existing.setAction(custodyRecord.getAction());
        }
        if (custodyRecord.getLocation() != null) {
            existing.setLocation(custodyRecord.getLocation());
        }
        if (custodyRecord.getNotes() != null) {
            existing.setNotes(custodyRecord.getNotes());
        }
        UserSnapshot custodian = this.resolveCustodian(
                custodyRecord.getCustodian() == null ? existing.getCustodian() : custodyRecord.getCustodian());
        existing.setCustodian(custodian);
        return this.withCustodian(this.custodyRecordGateway.update(existing), custodian);
    }

    public List<CustodianActivityReport> findActivityReport() {
        List<CustodianActivityReport> reports = this.custodyRecordGateway.findActivityReport();
        if (reports.isEmpty()) {
            return List.of();
        }
        Map<UUID, UserSnapshot> custodiansById = this.findCustodiansById(reports.stream()
                .map(report -> report.getCustodian().getId())
                .collect(Collectors.toSet()));
        reports.forEach(report -> report.setCustodian(
                this.readCustodian(custodiansById, report.getCustodian().getId())));
        return reports;
    }

    private CustodyRecord readStored(UUID id) {
        return this.custodyRecordGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Custody record id not found: " + id));
    }

    private UserSnapshot resolveCustodian(UserSnapshot custodian) {
        return this.userFinder.read(custodian.getId());
    }

    private CustodyRecord withCustodian(CustodyRecord custodyRecord, UserSnapshot custodian) {
        custodyRecord.setCustodian(custodian);
        return custodyRecord;
    }

    private Map<UUID, UserSnapshot> findCustodiansById(Set<UUID> custodianIds) {
        return this.userFinder.findByIds(custodianIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
    }

    private UserSnapshot readCustodian(Map<UUID, UserSnapshot> custodiansById, UUID custodianId) {
        UserSnapshot custodian = custodiansById.get(custodianId);
        if (custodian == null) {
            throw new NotFoundException("User id not found: " + custodianId);
        }
        return custodian;
    }

    private CustodyRecord toSummary(CustodyRecord custodyRecord, Map<UUID, UserSnapshot> custodiansById) {
        UserSnapshot custodian = this.readCustodian(custodiansById, custodyRecord.getCustodian().getId());
        return this.withCustodian(custodyRecord, custodian).ofSummary();
    }
}