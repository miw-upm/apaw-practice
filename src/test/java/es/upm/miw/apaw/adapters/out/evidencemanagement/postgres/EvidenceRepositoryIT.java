package es.upm.miw.apaw.adapters.out.evidencemanagement.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodianActivityReport;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import es.upm.miw.apaw.domain.model.evidencemanagement.EvidenceStatus;
import es.upm.miw.apaw.domain.model.evidencemanagement.EvidenceType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class EvidenceRepositoryIT {
    @Autowired
    private EvidenceRepository evidenceRepository;
    @Autowired
    private CustodyRecordRepository custodyRecordRepository;

    @Test
    void testFindCustodianActivityReportGroupsAndAggregates() {
        UUID custodianId = UUID.randomUUID();
        UUID unreferencedCustodianId = UUID.randomUUID();
        CustodyRecordEntity first = this.saveRecord(custodianId, 30);
        CustodyRecordEntity second = this.saveRecord(custodianId, null);
        CustodyRecordEntity third = this.saveRecord(custodianId, 45);
        this.saveRecord(custodianId, 100);
        this.saveRecord(unreferencedCustodianId, 50);
        this.saveEvidenceWith(first, second);
        this.saveEvidenceWith(third);

        List<CustodianActivityReport> report = this.evidenceRepository.findCustodianActivityReport();

        assertThat(report).filteredOn(item -> item.getCustodian().getId().equals(custodianId))
                .singleElement().usingRecursiveComparison()
                .isEqualTo(new CustodianActivityReport(custodianId, 3, 2, 75));
        assertThat(report).extracting(item -> item.getCustodian().getId())
                .doesNotContain(unreferencedCustodianId);
    }

    @Test
    void testFindCustodianActivityReportSortedByTotalDurationThenCustodianId() {
        UUID lessActive = UUID.randomUUID();
        UUID moreActive = UUID.randomUUID();
        UUID firstTied = new UUID(0L, 1L);
        UUID secondTied = new UUID(0L, 2L);
        this.saveEvidenceWith(this.saveRecord(lessActive, 10));
        this.saveEvidenceWith(this.saveRecord(moreActive, 20));
        this.saveEvidenceWith(this.saveRecord(secondTied, 15));
        this.saveEvidenceWith(this.saveRecord(firstTied, 15));

        List<CustodianActivityReport> report = this.evidenceRepository.findCustodianActivityReport();

        assertThat(report).extracting(CustodianActivityReport::getTotalDurationMinutes)
                .isSortedAccordingTo(Comparator.reverseOrder());
        assertThat(report).extracting(item -> item.getCustodian().getId())
                .containsSubsequence(moreActive, firstTied, secondTied, lessActive);
    }

    private CustodyRecordEntity saveRecord(UUID custodianId, Integer durationMinutes) {
        return this.custodyRecordRepository.save(new CustodyRecordEntity(CustodyRecord.builder()
                .id(UUID.randomUUID()).recordedAt(LocalDateTime.of(2025, 1, 1, 8, 0))
                .durationMinutes(durationMinutes).action("IT action " + UUID.randomUUID())
                .custodian(UserSnapshot.builder().id(custodianId).build()).build()));
    }

    private void saveEvidenceWith(CustodyRecordEntity... custodyRecords) {
        this.evidenceRepository.saveAndFlush(EvidenceEntity.builder().id(UUID.randomUUID())
                .title("Evidence " + UUID.randomUUID()).evidenceType(EvidenceType.PHYSICAL)
                .status(EvidenceStatus.REGISTERED).confidential(false)
                .collectionDate(LocalDateTime.of(2025, 1, 1, 8, 0))
                .custodyRecords(List.of(custodyRecords)).build());
    }
}