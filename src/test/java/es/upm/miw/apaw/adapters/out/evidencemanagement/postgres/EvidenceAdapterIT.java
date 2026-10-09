package es.upm.miw.apaw.adapters.out.evidencemanagement.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import es.upm.miw.apaw.domain.model.evidencemanagement.Evidence;
import es.upm.miw.apaw.domain.model.evidencemanagement.EvidenceFindCriteria;
import es.upm.miw.apaw.domain.model.evidencemanagement.EvidenceStatus;
import es.upm.miw.apaw.domain.model.evidencemanagement.EvidenceType;
import es.upm.miw.apaw.domain.ports.out.evidencemanagement.CustodyRecordGateway;
import es.upm.miw.apaw.domain.ports.out.evidencemanagement.EvidenceGateway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import static es.upm.miw.apaw.config.seeders.EvidenceSeederForDev.CUSTODIAN_ID_0;
import static es.upm.miw.apaw.config.seeders.EvidenceSeederForDev.EVIDENCE_ID_0;
import static es.upm.miw.apaw.config.seeders.EvidenceSeederForDev.EVIDENCE_ID_1;
import static es.upm.miw.apaw.config.seeders.EvidenceSeederForDev.EVIDENCE_ID_2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class EvidenceAdapterIT {
    @Autowired
    private EvidenceGateway evidenceGateway;
    @Autowired
    private CustodyRecordGateway custodyRecordGateway;
    @Autowired
    private EvidenceRepository evidenceRepository;

    @Test
    void testCreateConvertsAllFields() {
        Evidence evidence = this.newEvidence(List.of(this.createCustodyRecord(), this.createCustodyRecord()));
        assertThat(this.evidenceGateway.create(evidence)).usingRecursiveComparison().isEqualTo(evidence);
        EvidenceEntity stored = this.evidenceRepository.findById(evidence.getId()).orElseThrow();
        assertThat(stored).usingRecursiveComparison().ignoringFields("custodyRecords").isEqualTo(evidence);
    }

    @Test
    void testCreateOnlyRequiredFields() {
        Evidence evidence = this.newEvidence(List.of());
        evidence.setDescription(null);
        evidence.setSource(null);
        assertThat(this.evidenceGateway.create(evidence)).usingRecursiveComparison().isEqualTo(evidence);
        EvidenceEntity stored = this.evidenceRepository.findById(evidence.getId()).orElseThrow();
        assertThat(stored.getDescription()).isNull();
        assertThat(stored.getSource()).isNull();
    }

    @Test
    void testCreateDoesNotModifyCustodyRecords() {
        CustodyRecord custodyRecord = this.createCustodyRecord();
        this.evidenceGateway.create(this.newEvidence(List.of(custodyRecord)));
        assertThat(this.custodyRecordGateway.read(custodyRecord.getId())).isPresent().get()
                .usingRecursiveComparison().isEqualTo(custodyRecord);
    }

    @Test
    void testCreateWithoutTitleViolatesConstraint() {
        this.assertViolatesConstraint(evidence -> evidence.setTitle(null));
    }

    @Test
    void testCreateWithoutEvidenceTypeViolatesConstraint() {
        this.assertViolatesConstraint(evidence -> evidence.setEvidenceType(null));
    }

    @Test
    void testCreateWithoutStatusViolatesConstraint() {
        this.assertViolatesConstraint(evidence -> evidence.setStatus(null));
    }

    @Test
    void testCreateWithoutConfidentialViolatesConstraint() {
        this.assertViolatesConstraint(evidence -> evidence.setConfidential(null));
    }

    @Test
    void testCreateWithoutCollectionDateViolatesConstraint() {
        this.assertViolatesConstraint(evidence -> evidence.setCollectionDate(null));
    }

    @Test
    void testFindByConfidential() {
        Evidence confidential = this.createEvidence(true);
        Evidence open = this.createEvidence(false);

        assertThat(this.findIds(EvidenceFindCriteria.builder().confidential(true).build()))
                .contains(confidential.getId()).doesNotContain(open.getId());
        assertThat(this.findIds(EvidenceFindCriteria.builder().confidential(false).build()))
                .contains(open.getId()).doesNotContain(confidential.getId());
    }

    @Test
    void testFindByActionIgnoresCaseAndSurroundingSpaces() {
        String action = "Action " + UUID.randomUUID();
        Evidence matching = this.createEvidence(false, this.createCustodyRecord(action, 10), this.createCustodyRecord(5));
        this.createEvidence(false, this.createCustodyRecord(5));

        assertThat(this.findIds(EvidenceFindCriteria.builder().action("  " + action.toUpperCase() + " ").build()))
                .containsExactly(matching.getId());
    }

    @Test
    void testFindByLongCustody() {
        Evidence longCustody = this.createEvidence(false, this.createCustodyRecord(120), this.createCustodyRecord(300));
        Evidence shortCustody = this.createEvidence(false, this.createCustodyRecord(119));
        Evidence withoutDuration = this.createEvidence(false, this.createCustodyRecord(null));
        Evidence mixed = this.createEvidence(false, this.createCustodyRecord(200), this.createCustodyRecord(10));

        assertThat(this.findIds(EvidenceFindCriteria.builder().longCustody(true).build()))
                .containsOnlyOnce(longCustody.getId(), mixed.getId())
                .doesNotContain(shortCustody.getId(), withoutDuration.getId());
        assertThat(this.findIds(EvidenceFindCriteria.builder().longCustody(false).build()))
                .containsOnlyOnce(shortCustody.getId(), withoutDuration.getId(), mixed.getId())
                .doesNotContain(longCustody.getId());
    }

    @Test
    void testFindByActionAndLongCustodyEvaluatesSameCustodyRecord() {
        String sealed = "Sealed " + UUID.randomUUID();
        String stored = "Stored " + UUID.randomUUID();
        Evidence matching = this.createEvidence(false,
                this.createCustodyRecord(sealed, 200), this.createCustodyRecord(stored, 10));
        this.createEvidence(false, this.createCustodyRecord(sealed, 10), this.createCustodyRecord(stored, 200));

        List<Evidence> found = this.evidenceGateway
                .find(EvidenceFindCriteria.builder().action(sealed).longCustody(true).build());

        assertThat(found).singleElement().satisfies(evidence -> {
            assertThat(evidence.getId()).isEqualTo(matching.getId());
            assertThat(evidence.getCustodyRecords()).hasSize(2);
        });
    }

    @Test
    void testFindWithoutEffectiveCriteriaReturnsAll() {
        Evidence withoutRecords = this.createEvidence(true);

        assertThat(this.findIds(new EvidenceFindCriteria()))
                .contains(EVIDENCE_ID_0, EVIDENCE_ID_1, EVIDENCE_ID_2, withoutRecords.getId());
        assertThat(this.findIds(EvidenceFindCriteria.builder().action(" ").build()))
                .contains(EVIDENCE_ID_0, EVIDENCE_ID_1, EVIDENCE_ID_2, withoutRecords.getId());
    }

    @Test
    void testFindSortedByTitle() {
        String prefix = "Order " + UUID.randomUUID();
        Evidence second = this.newEvidence(List.of());
        second.setTitle(prefix + " B");
        Evidence first = this.newEvidence(List.of());
        first.setTitle(prefix + " A");
        this.evidenceGateway.create(second);
        this.evidenceGateway.create(first);

        assertThat(this.findIds(new EvidenceFindCriteria())).containsSubsequence(first.getId(), second.getId());
    }

    private void assertViolatesConstraint(Consumer<Evidence> breaker) {
        Evidence evidence = this.newEvidence(List.of());
        breaker.accept(evidence);
        assertThatThrownBy(() -> this.evidenceGateway.create(evidence))
                .isInstanceOf(DataIntegrityViolationException.class);
        assertThat(this.evidenceRepository.existsById(evidence.getId())).isFalse();
    }

    private Evidence newEvidence(List<CustodyRecord> custodyRecords) {
        return Evidence.builder().id(UUID.randomUUID()).title("IT evidence " + UUID.randomUUID())
                .description("IT description").evidenceType(EvidenceType.DIGITAL)
                .status(EvidenceStatus.UNDER_REVIEW).collectionDate(LocalDateTime.of(2025, 1, 1, 8, 0))
                .source("IT source").confidential(true).custodyRecords(custodyRecords).build();
    }

    private CustodyRecord createCustodyRecord() {
        return this.createCustodyRecord((Integer) null);
    }

    private CustodyRecord createCustodyRecord(Integer durationMinutes) {
        return this.createCustodyRecord("IT action " + UUID.randomUUID(), durationMinutes);
    }

    private CustodyRecord createCustodyRecord(String action, Integer durationMinutes) {
        return this.custodyRecordGateway.create(CustodyRecord.builder().id(UUID.randomUUID())
                .recordedAt(LocalDateTime.of(2025, 1, 1, 8, 0)).action(action).durationMinutes(durationMinutes)
                .custodian(UserSnapshot.builder().id(CUSTODIAN_ID_0).build()).build());
    }

    private Evidence createEvidence(Boolean confidential, CustodyRecord... custodyRecords) {
        Evidence evidence = this.newEvidence(List.of(custodyRecords));
        evidence.setConfidential(confidential);
        return this.evidenceGateway.create(evidence);
    }

    private List<UUID> findIds(EvidenceFindCriteria criteria) {
        return this.evidenceGateway.find(criteria).stream().map(Evidence::getId).toList();
    }
}