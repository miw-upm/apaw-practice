package es.upm.miw.apaw.adapters.out.evidencemanagement.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodianActivityReport;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import es.upm.miw.apaw.domain.model.evidencemanagement.EvidenceStatus;
import es.upm.miw.apaw.domain.model.evidencemanagement.EvidenceType;
import es.upm.miw.apaw.domain.ports.out.evidencemanagement.CustodyRecordGateway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.EvidenceSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class CustodyRecordAdapterIT {
    @Autowired
    private CustodyRecordGateway custodyRecordGateway;
    @Autowired
    private EvidenceRepository evidenceRepository;

    @Test
    void testCreateAndReadConvertsAllFields() {
        CustodyRecord custodyRecord = this.newRecord();
        assertThat(this.custodyRecordGateway.create(custodyRecord))
                .usingRecursiveComparison().isEqualTo(custodyRecord);
        assertThat(this.custodyRecordGateway.read(custodyRecord.getId())).isPresent().get()
                .usingRecursiveComparison().isEqualTo(custodyRecord);
    }

    @Test
    void testCreateOnlyRequiredFields() {
        CustodyRecord custodyRecord = this.newRecord();
        custodyRecord.setDurationMinutes(null);
        custodyRecord.setLocation(null);
        custodyRecord.setNotes(null);
        this.custodyRecordGateway.create(custodyRecord);
        assertThat(this.custodyRecordGateway.read(custodyRecord.getId())).isPresent().get()
                .usingRecursiveComparison().isEqualTo(custodyRecord);
    }

    @Test
    void testCreatePersistsCustodianAsIdOnly() {
        CustodyRecord custodyRecord = this.newRecord();
        custodyRecord.setCustodian(UserSnapshot.builder().id(CUSTODIAN_ID_0).mobile("600000000")
                .firstName("Ana").familyName("Lopez").email("ana@example.com").build());
        this.custodyRecordGateway.create(custodyRecord);
        assertThat(this.custodyRecordGateway.read(custodyRecord.getId())).isPresent().get()
                .extracting(CustodyRecord::getCustodian).usingRecursiveComparison()
                .isEqualTo(UserSnapshot.builder().id(CUSTODIAN_ID_0).build());
    }

    @Test
    void testCreateWithoutActionViolatesConstraint() {
        CustodyRecord custodyRecord = this.newRecord();
        custodyRecord.setAction(null);
        assertThatThrownBy(() -> this.custodyRecordGateway.create(custodyRecord))
                .isInstanceOf(DataIntegrityViolationException.class);
        assertThat(this.custodyRecordGateway.read(custodyRecord.getId())).isEmpty();
    }

    @Test
    void testUpdateReplacesRecordWithoutDuplicating() {
        CustodyRecord custodyRecord = this.custodyRecordGateway.create(this.newRecord());
        custodyRecord.setAction("Updated action");
        custodyRecord.setLocation(null);
        custodyRecord.setCustodian(UserSnapshot.builder().id(CUSTODIAN_ID_1).build());
        this.custodyRecordGateway.update(custodyRecord);
        assertThat(this.custodyRecordGateway.read(custodyRecord.getId())).isPresent().get()
                .usingRecursiveComparison().isEqualTo(custodyRecord);
        assertThat(this.custodyRecordGateway.findAll()).filteredOn(stored -> stored.getId().equals(custodyRecord.getId()))
                .hasSize(1);
    }

    @Test
    void testFindAllOrderedByRecordedAtThenId() {
        CustodyRecord extra = this.newRecord();
        extra.setRecordedAt(LocalDateTime.of(2025, 5, 10, 9, 0));
        this.custodyRecordGateway.create(extra);
        assertThat(this.custodyRecordGateway.findAll()).extracting(CustodyRecord::getId)
                .containsSubsequence(ID_0, ID_1, ID_2, ID_3, ID_4, extra.getId(), ID_5);
    }

    @Test
    void testIsReferenced() {
        CustodyRecord custodyRecord = this.custodyRecordGateway.create(this.newRecord());
        assertThat(this.custodyRecordGateway.isReferenced(custodyRecord.getId())).isFalse();
        this.saveEvidenceWith(custodyRecord);
        assertThat(this.custodyRecordGateway.isReferenced(custodyRecord.getId())).isTrue();
    }

    @Test
    void testIsReferencedMissingRecord() {
        assertThat(this.custodyRecordGateway.isReferenced(UUID.randomUUID())).isFalse();
    }

    @Test
    void testUpdateKeepsRecordReferenced() {
        CustodyRecord custodyRecord = this.custodyRecordGateway.create(this.newRecord());
        this.saveEvidenceWith(custodyRecord);
        custodyRecord.setAction("Updated action");
        this.custodyRecordGateway.update(custodyRecord);
        assertThat(this.custodyRecordGateway.isReferenced(custodyRecord.getId())).isTrue();
    }

    private void saveEvidenceWith(CustodyRecord custodyRecord) {
        this.evidenceRepository.saveAndFlush(EvidenceEntity.builder().id(UUID.randomUUID())
                .title("Evidence " + UUID.randomUUID()).evidenceType(EvidenceType.PHYSICAL)
                .status(EvidenceStatus.REGISTERED).confidential(false)
                .collectionDate(LocalDateTime.of(2025, 1, 1, 8, 0))
                .custodyRecords(List.of(new CustodyRecordEntity(custodyRecord))).build());
    }

    private CustodyRecord newRecord() {
        return CustodyRecord.builder().id(UUID.randomUUID()).recordedAt(LocalDateTime.of(2025, 1, 1, 8, 0))
                .durationMinutes(20).action("IT action " + UUID.randomUUID()).location("IT location")
                .notes("IT notes").custodian(UserSnapshot.builder().id(CUSTODIAN_ID_0).build()).build();
    }

    @Test
    void testDeleteById() {
        CustodyRecord custodyRecord = this.custodyRecordGateway.create(this.newRecord());
        this.custodyRecordGateway.deleteById(custodyRecord.getId());
        assertThat(this.custodyRecordGateway.read(custodyRecord.getId())).isEmpty();
    }

    @Test
    void testFindActivityReportReturnsCustodianAsIdOnly() {
        UUID custodianId = RECORD_0.getCustodian().getId();
        assertThat(this.custodyRecordGateway.findActivityReport())
                .filteredOn(report -> report.getCustodian().getId().equals(custodianId))
                .singleElement().extracting(CustodianActivityReport::getCustodian)
                .usingRecursiveComparison()
                .isEqualTo(UserSnapshot.builder().id(custodianId).build());
    }
}