package es.upm.miw.apaw.domain.services.evidencemanagement;

import es.upm.miw.apaw.adapters.out.evidencemanagement.postgres.EvidenceEntity;
import es.upm.miw.apaw.adapters.out.evidencemanagement.postgres.EvidenceRepository;
import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.evidencemanagement.CreationEvidence;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import es.upm.miw.apaw.domain.model.evidencemanagement.Evidence;
import es.upm.miw.apaw.domain.model.evidencemanagement.EvidenceStatus;
import es.upm.miw.apaw.domain.model.evidencemanagement.EvidenceType;
import es.upm.miw.apaw.domain.ports.out.evidencemanagement.CustodyRecordGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.EvidenceSeederForDev.ID_0;
import static es.upm.miw.apaw.config.seeders.EvidenceSeederForDev.CUSTODIAN_ID_0;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verifyNoInteractions;

@SpringBootTest
@ActiveProfiles("test")
class EvidenceServiceIT {
    private static final LocalDateTime COLLECTION_DATE = LocalDateTime.of(2025, 1, 1, 8, 0);

    @Autowired
    private EvidenceService evidenceService;
    @Autowired
    private EvidenceRepository evidenceRepository;
    @Autowired
    private CustodyRecordGateway custodyRecordGateway;
    @MockitoBean
    private UserFinder userFinder;

    @Test
    void testCreateAppliesDefaultsAndAssociatesCustodyRecords() {
        CustodyRecord first = this.createCustodyRecord();
        CustodyRecord second = this.createCustodyRecord();
        CreationEvidence creation = this.creation(List.of(first.getId(), second.getId()));
        Evidence evidence = this.evidenceService.create(creation);
        assertThat(evidence.getId()).isNotNull();
        assertThat(evidence.getStatus()).isEqualTo(EvidenceStatus.REGISTERED);
        assertThat(evidence.getConfidential()).isFalse();
        assertThat(evidence.getCustodyRecords()).extracting(CustodyRecord::getId)
                .containsExactly(first.getId(), second.getId());
        EvidenceEntity stored = this.evidenceRepository.findById(evidence.getId()).orElseThrow();
        assertThat(stored.getStatus()).isEqualTo(EvidenceStatus.REGISTERED);
        assertThat(stored.getConfidential()).isFalse();
        assertThat(this.custodyRecordGateway.isReferenced(first.getId())).isTrue();
        assertThat(this.custodyRecordGateway.isReferenced(second.getId())).isTrue();
    }

    @Test
    void testCreateKeepsProvidedValues() {
        CreationEvidence creation = CreationEvidence.builder().title("IT evidence " + UUID.randomUUID())
                .description("IT description").evidenceType(EvidenceType.DIGITAL)
                .collectionDate(COLLECTION_DATE).source("IT source").confidential(true)
                .custodyRecordIds(List.of()).build();
        Evidence evidence = this.evidenceService.create(creation);
        assertThat(evidence).extracting(Evidence::getTitle, Evidence::getDescription, Evidence::getEvidenceType,
                        Evidence::getCollectionDate, Evidence::getSource, Evidence::getConfidential)
                .containsExactly(creation.getTitle(), "IT description", EvidenceType.DIGITAL,
                        COLLECTION_DATE, "IT source", true);
    }

    @Test
    void testCreateWithoutCustodyRecords() {
        Evidence evidence = this.evidenceService.create(this.creation(List.of()));
        assertThat(evidence.getId()).isNotNull();
        assertThat(evidence.getCustodyRecords()).isEmpty();
    }

    @Test
    void testCreateCustodyRecordNotFoundAssociatesNothing() {
        CustodyRecord existing = this.createCustodyRecord();
        UUID missingId = UUID.randomUUID();
        CreationEvidence creation = this.creation(List.of(existing.getId(), missingId));
        assertThatThrownBy(() -> this.evidenceService.create(creation))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(missingId.toString());
        assertThat(this.custodyRecordGateway.isReferenced(existing.getId())).isFalse();
        this.assertNotStored(creation);
    }

    @Test
    void testCreateCustodyRecordAlreadyAssociated() {
        CustodyRecord free = this.createCustodyRecord();
        CreationEvidence creation = this.creation(List.of(free.getId(), ID_0));
        assertThatThrownBy(() -> this.evidenceService.create(creation))
                .isInstanceOf(ConflictException.class).hasMessageContaining(ID_0.toString());
        assertThat(this.custodyRecordGateway.isReferenced(free.getId())).isFalse();
        this.assertNotStored(creation);
    }

    @Test
    void testCreateRepeatedCustodyRecordId() {
        CustodyRecord custodyRecord = this.createCustodyRecord();
        CreationEvidence creation = this.creation(List.of(custodyRecord.getId(), custodyRecord.getId()));
        assertThatThrownBy(() -> this.evidenceService.create(creation))
                .isInstanceOf(BadRequestException.class).hasMessageContaining(custodyRecord.getId().toString());
        assertThat(this.custodyRecordGateway.isReferenced(custodyRecord.getId())).isFalse();
        this.assertNotStored(creation);
    }

    @Test
    void testCreateDoesNotQueryUsers() {
        CustodyRecord custodyRecord = this.createCustodyRecord();
        this.evidenceService.create(this.creation(List.of(custodyRecord.getId())));
        verifyNoInteractions(this.userFinder);
    }

    private void assertNotStored(CreationEvidence creation) {
        assertThat(this.evidenceRepository.findAll()).extracting(EvidenceEntity::getTitle)
                .doesNotContain(creation.getTitle());
    }

    private CreationEvidence creation(List<UUID> custodyRecordIds) {
        return CreationEvidence.builder().title("IT evidence " + UUID.randomUUID())
                .evidenceType(EvidenceType.PHYSICAL).collectionDate(COLLECTION_DATE)
                .custodyRecordIds(custodyRecordIds).build();
    }

    private CustodyRecord createCustodyRecord() {
        CustodyRecord custodyRecord = CustodyRecord.builder().action("IT action " + UUID.randomUUID())
                .custodian(UserSnapshot.builder().id(CUSTODIAN_ID_0).build()).build();
        custodyRecord.doDefault();
        return this.custodyRecordGateway.create(custodyRecord);
    }
}
