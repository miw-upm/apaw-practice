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
import es.upm.miw.apaw.domain.model.evidencemanagement.EvidenceFindCriteria;
import es.upm.miw.apaw.domain.model.evidencemanagement.EvidenceStatus;
import es.upm.miw.apaw.domain.model.evidencemanagement.EvidenceType;
import es.upm.miw.apaw.domain.ports.out.evidencemanagement.CustodyRecordGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.util.Set;

import static es.upm.miw.apaw.config.seeders.EvidenceSeederForDev.ID_0;
import static es.upm.miw.apaw.config.seeders.EvidenceSeederForDev.CUSTODIAN_ID_0;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

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

    @Test
    void testFindHydratesCustodiansWithSingleCall() {
        UUID firstCustodianId = UUID.randomUUID();
        UUID secondCustodianId = UUID.randomUUID();
        String action = "IT action " + UUID.randomUUID();
        Evidence evidence = this.createEvidence(this.createCustodyRecord(firstCustodianId, action),
                this.createCustodyRecord(secondCustodianId));
        this.stubFindByIds(Map.of(firstCustodianId, "Ana", secondCustodianId, "Luis"));

        List<Evidence> evidences = this.evidenceService.find(EvidenceFindCriteria.builder().action(action).build());

        assertThat(evidences).singleElement().satisfies(found -> {
            assertThat(found.getId()).isEqualTo(evidence.getId());
            assertThat(found.getCustodyRecords()).extracting(custodyRecord -> custodyRecord.getCustodian().getFirstName())
                    .containsExactlyInAnyOrder("Ana", "Luis");
            assertThat(found.getCustodyRecords()).allSatisfy(custodyRecord -> {
                assertThat(custodyRecord.getCustodian().getMobile()).isEqualTo("600000000");
                assertThat(custodyRecord.getCustodian().getFamilyName()).isNull();
            });
        });
        verify(this.userFinder, times(1)).findByIds(Set.of(firstCustodianId, secondCustodianId));
        verifyNoMoreInteractions(this.userFinder);
    }

    @Test
    void testFindByCustodianFirstNameMatchesAnyRecordIgnoringCaseAndSpaces() {
        String firstName = "Custodian " + UUID.randomUUID();
        UUID matchingCustodianId = UUID.randomUUID();
        UUID otherCustodianId = UUID.randomUUID();
        Evidence matching = this.createEvidence(this.createCustodyRecord(matchingCustodianId),
                this.createCustodyRecord(otherCustodianId));
        this.createEvidence(this.createCustodyRecord(otherCustodianId));
        this.stubFindByIds(Map.of(matchingCustodianId, firstName, otherCustodianId, "Other"));

        List<Evidence> evidences = this.evidenceService.find(
                EvidenceFindCriteria.builder().custodianFirstName("  " + firstName.toUpperCase() + " ").build());

        assertThat(evidences).singleElement().satisfies(found -> {
            assertThat(found.getId()).isEqualTo(matching.getId());
            assertThat(found.getCustodyRecords()).extracting(custodyRecord -> custodyRecord.getCustodian().getFirstName())
                    .containsExactlyInAnyOrder(firstName, "Other");
        });
        verify(this.userFinder, times(1)).findByIds(any());
    }

    @Test
    void testFindIgnoresBlankCustodianFirstName() {
        String action = "IT action " + UUID.randomUUID();
        Evidence evidence = this.createEvidence(this.createCustodyRecord(UUID.randomUUID(), action));
        this.stubFindByIds(Map.of());

        assertThat(this.evidenceService.find(EvidenceFindCriteria.builder().action(action).custodianFirstName(" ").build()))
                .extracting(Evidence::getId).containsExactly(evidence.getId());
    }

    @Test
    void testFindCustodianNotFound() {
        UUID custodianId = UUID.randomUUID();
        String action = "IT action " + UUID.randomUUID();
        this.createEvidence(this.createCustodyRecord(custodianId, action));
        when(this.userFinder.findByIds(any())).thenReturn(List.of());
        EvidenceFindCriteria criteria = EvidenceFindCriteria.builder().action(action).build();

        assertThatThrownBy(() -> this.evidenceService.find(criteria))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(custodianId.toString());
    }

    @Test
    @Transactional
    void testFindEvidencesWithoutCustodyRecordsDoesNotQueryUsers() {
        this.evidenceRepository.deleteAll();
        this.evidenceRepository.flush();
        Evidence evidence = this.evidenceService.create(this.creation(List.of()));

        assertThat(this.evidenceService.find(new EvidenceFindCriteria())).extracting(Evidence::getId)
                .containsExactly(evidence.getId());
        assertThat(this.evidenceService.find(EvidenceFindCriteria.builder().custodianFirstName("Ana").build()))
                .isEmpty();

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
        return this.createCustodyRecord(CUSTODIAN_ID_0);
    }

    private CustodyRecord createCustodyRecord(UUID custodianId) {
        return this.createCustodyRecord(custodianId, "IT action " + UUID.randomUUID());
    }

    private CustodyRecord createCustodyRecord(UUID custodianId, String action) {
        CustodyRecord custodyRecord = CustodyRecord.builder().action(action)
                .custodian(UserSnapshot.builder().id(custodianId).build()).build();
        custodyRecord.doDefault();
        return this.custodyRecordGateway.create(custodyRecord);
    }

    private Evidence createEvidence(CustodyRecord... custodyRecords) {
        return this.evidenceService.create(this.creation(
                Arrays.stream(custodyRecords).map(CustodyRecord::getId).toList()));
    }

    private void stubFindByIds(Map<UUID, String> firstNames) {
        when(this.userFinder.findByIds(any())).thenAnswer(invocation -> {
            Collection<UUID> ids = invocation.getArgument(0);
            return ids.stream().map(id -> UserSnapshot.builder().id(id).mobile("600000000")
                    .firstName(firstNames.getOrDefault(id, "Unknown")).familyName("Lopez").build()).toList();
        });
    }
}
