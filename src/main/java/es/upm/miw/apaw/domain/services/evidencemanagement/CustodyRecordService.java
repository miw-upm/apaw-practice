package es.upm.miw.apaw.domain.services.evidencemanagement;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodianActivityReport;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import es.upm.miw.apaw.domain.ports.out.evidencemanagement.CustodyRecordGateway;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustodyRecordService {
    private final CustodyRecordGateway custodyRecordGateway;
    private final UserFinder userFinder;

    public CustodyRecord create(CustodyRecord custodyRecord) {
        this.assertCustodianExists(custodyRecord.getCustodian());
        custodyRecord.doDefault();
        return this.custodyRecordGateway.create(custodyRecord);
    }

    public CustodyRecord read(UUID id) {
        return this.custodyRecordGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Custody record id not found: " + id));
    }

    public CustodyRecord update(UUID id, CustodyRecord custodyRecord) {
        CustodyRecord existing = this.read(id);
        this.assertCustodianExists(custodyRecord.getCustodian());
        existing.setDurationMinutes(custodyRecord.getDurationMinutes());
        existing.setAction(custodyRecord.getAction());
        existing.setLocation(custodyRecord.getLocation());
        existing.setNotes(custodyRecord.getNotes());
        existing.setCustodian(custodyRecord.getCustodian());
        return this.custodyRecordGateway.update(existing);
    }

    public void delete(UUID id) {
        if (this.custodyRecordGateway.isReferenced(id)) {
            throw new ConflictException("Custody record is referenced by an evidence: " + id);
        }
        this.custodyRecordGateway.deleteById(id);
    }

    public List<CustodyRecord> findAll() {
        return this.custodyRecordGateway.findAll();
    }

    public CustodyRecord patch(UUID id, CustodyRecord custodyRecord) {
        CustodyRecord existing = this.read(id);
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
        if (custodyRecord.getCustodian() != null) {
            this.assertCustodianExists(custodyRecord.getCustodian());
            existing.setCustodian(custodyRecord.getCustodian());
        }
        return this.custodyRecordGateway.update(existing);
    }

    public List<CustodianActivityReport> findActivityReport() {
        List<CustodianActivityReport> reports = this.custodyRecordGateway.findActivityReport();
        if (reports.isEmpty()) {
            return List.of();
        }
        Set<UUID> custodianIds = reports.stream()
                .map(report -> report.getCustodian().getId())
                .collect(Collectors.toSet());
        Map<UUID, UserSnapshot> custodiansById = this.userFinder.findByIds(custodianIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        reports.forEach(report -> report.setCustodian(this.readCustodian(custodiansById, report)));
        return reports;
    }

    private UserSnapshot readCustodian(Map<UUID, UserSnapshot> custodiansById, CustodianActivityReport report) {
        UUID custodianId = report.getCustodian().getId();
        UserSnapshot custodian = custodiansById.get(custodianId);
        if (custodian == null) {
            throw new NotFoundException("User id not found: " + custodianId);
        }
        return custodian;
    }

    private void assertCustodianExists(UserSnapshot custodian) {
        this.userFinder.read(custodian.getId());
    }

}
