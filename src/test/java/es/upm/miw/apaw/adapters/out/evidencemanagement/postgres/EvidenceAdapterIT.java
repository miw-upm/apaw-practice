package es.upm.miw.apaw.adapters.out.evidencemanagement.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import es.upm.miw.apaw.domain.model.evidencemanagement.Evidence;
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

import static es.upm.miw.apaw.config.seeders.CustodyRecordSeederForDev.CUSTODIAN_ID_0;
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
        return this.custodyRecordGateway.create(CustodyRecord.builder().id(UUID.randomUUID())
                .recordedAt(LocalDateTime.of(2025, 1, 1, 8, 0)).action("IT action " + UUID.randomUUID())
                .custodian(UserSnapshot.builder().id(CUSTODIAN_ID_0).build()).build());
    }
}