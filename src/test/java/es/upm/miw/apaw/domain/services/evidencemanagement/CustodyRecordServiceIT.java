package es.upm.miw.apaw.domain.services.evidencemanagement;

import es.upm.miw.apaw.adapters.out.evidencemanagement.postgres.CustodyRecordEntity;
import es.upm.miw.apaw.adapters.out.evidencemanagement.postgres.EvidenceEntity;
import es.upm.miw.apaw.adapters.out.evidencemanagement.postgres.EvidenceRepository;
import es.upm.miw.apaw.domain.exceptions.BadGatewayException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import es.upm.miw.apaw.domain.model.evidencemanagement.EvidenceStatus;
import es.upm.miw.apaw.domain.model.evidencemanagement.EvidenceType;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.EvidenceSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class CustodyRecordServiceIT {
    private static final UUID MISSING_CUSTODIAN_ID = UUID.randomUUID();

    @Autowired
    private CustodyRecordService custodyRecordService;
    @Autowired
    private EvidenceRepository evidenceRepository;
    @MockitoBean
    private UserFinder userFinder;

    @BeforeEach
    void setUp() {
        when(this.userFinder.read(any(UUID.class)))
                .thenAnswer(invocation -> UserSnapshot.builder().id(invocation.getArgument(0)).build());
        when(this.userFinder.read(MISSING_CUSTODIAN_ID))
                .thenThrow(new NotFoundException("Not found on read user by id " + MISSING_CUSTODIAN_ID));
    }

    @Test
    void testReadSeeder() {
        assertThat(this.custodyRecordService.read(ID_0)).usingRecursiveComparison().isEqualTo(RECORD_0);
    }

    @Test
    void testReadSeederWithoutOptionalFields() {
        assertThat(this.custodyRecordService.read(ID_2)).usingRecursiveComparison().isEqualTo(RECORD_2);
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.custodyRecordService.read(id))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testFindAllAllowsAdditionalRecordsAndKeepsOrder() {
        CustodyRecord extra = this.createRecord();
        List<CustodyRecord> records = this.custodyRecordService.findAll();
        assertThat(records).extracting(CustodyRecord::getId)
                .contains(ID_0, ID_1, ID_2, ID_3, ID_4, ID_5, extra.getId());
        assertThat(records).extracting(CustodyRecord::getId)
                .containsSubsequence(ID_0, ID_1, ID_2, ID_3, ID_4, ID_5);
        assertThat(this.custodyRecordService.findAll()).extracting(CustodyRecord::getId)
                .containsExactlyElementsOf(records.stream().map(CustodyRecord::getId).toList());
    }

    @Test
    void testCreate() {
        CustodyRecord created = this.custodyRecordService.create(this.newRecord());
        CustodyRecord stored = this.custodyRecordService.read(created.getId());
        assertThat(created.getId()).isNotNull();
        assertThat(stored.getRecordedAt()).isNotNull();
        assertThat(stored).usingRecursiveComparison().ignoringFields("recordedAt").isEqualTo(created);
    }

    @Test
    void testCreateIgnoresClientIdAndRecordedAt() {
        LocalDateTime oldDate = LocalDateTime.of(2000, 1, 1, 0, 0);
        CustodyRecord custodyRecord = this.newRecord();
        custodyRecord.setId(ID_0);
        custodyRecord.setRecordedAt(oldDate);
        CustodyRecord created = this.custodyRecordService.create(custodyRecord);
        assertThat(created.getId()).isNotEqualTo(ID_0);
        assertThat(created.getRecordedAt()).isAfter(oldDate);
        assertThat(this.custodyRecordService.read(ID_0)).usingRecursiveComparison().isEqualTo(RECORD_0);
    }

    @Test
    void testCreateCustodianNotFound() {
        CustodyRecord custodyRecord = this.newRecord();
        custodyRecord.setCustodian(UserSnapshot.builder().id(MISSING_CUSTODIAN_ID).build());
        assertThatThrownBy(() -> this.custodyRecordService.create(custodyRecord))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(MISSING_CUSTODIAN_ID.toString());
        assertThat(this.custodyRecordService.findAll()).extracting(CustodyRecord::getAction)
                .doesNotContain(custodyRecord.getAction());
    }

    @Test
    void testCreateUserServiceUnavailableSavesNothing() {
        UUID custodianId = UUID.randomUUID();
        when(this.userFinder.read(custodianId)).thenThrow(new BadGatewayException(
                "Cannot complete on read user by id " + custodianId, new RuntimeException("User service down")));
        CustodyRecord custodyRecord = this.newRecord();
        custodyRecord.setCustodian(UserSnapshot.builder().id(custodianId).build());
        assertThatThrownBy(() -> this.custodyRecordService.create(custodyRecord))
                .isInstanceOf(BadGatewayException.class);
        assertThat(this.custodyRecordService.findAll()).extracting(CustodyRecord::getAction)
                .doesNotContain(custodyRecord.getAction());
    }

    @Test
    void testUpdateReplacesMutableFields() {
        CustodyRecord original = this.createRecord();
        CustodyRecord replacement = CustodyRecord.builder().action("Updated " + UUID.randomUUID())
                .custodian(UserSnapshot.builder().id(CUSTODIAN_ID_1).build()).build();
        this.custodyRecordService.update(original.getId(), replacement);
        CustodyRecord updated = this.custodyRecordService.read(original.getId());
        assertThat(updated.getAction()).isEqualTo(replacement.getAction());
        assertThat(updated.getDurationMinutes()).isNull();
        assertThat(updated.getLocation()).isNull();
        assertThat(updated.getNotes()).isNull();
        assertThat(updated.getCustodian().getId()).isEqualTo(CUSTODIAN_ID_1);
        assertThat(updated.getId()).isEqualTo(original.getId());
        assertThat(updated.getRecordedAt()).isEqualTo(original.getRecordedAt());
    }

    @Test
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.custodyRecordService.update(id, this.newRecord()))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testUpdateCustodianNotFoundLeavesRecordUnchanged() {
        CustodyRecord original = this.createRecord();
        CustodyRecord replacement = this.newRecord();
        replacement.setCustodian(UserSnapshot.builder().id(MISSING_CUSTODIAN_ID).build());
        assertThatThrownBy(() -> this.custodyRecordService.update(original.getId(), replacement))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(MISSING_CUSTODIAN_ID.toString());
        assertThat(this.custodyRecordService.read(original.getId())).usingRecursiveComparison().isEqualTo(original);
    }

    @Test
    void testPatchChangesOnlyPresentFields() {
        CustodyRecord original = this.createRecord();
        this.custodyRecordService.patch(original.getId(), CustodyRecord.builder().notes("Patched notes").build());
        CustodyRecord patched = this.custodyRecordService.read(original.getId());
        assertThat(patched.getNotes()).isEqualTo("Patched notes");
        assertThat(patched).usingRecursiveComparison().ignoringFields("notes").isEqualTo(original);
    }

    @Test
    void testPatchWithoutFieldsChangesNothing() {
        CustodyRecord original = this.createRecord();
        this.custodyRecordService.patch(original.getId(), new CustodyRecord());
        assertThat(this.custodyRecordService.read(original.getId())).usingRecursiveComparison().isEqualTo(original);
    }

    @Test
    void testPatchCustodian() {
        CustodyRecord original = this.createRecord();
        this.custodyRecordService.patch(original.getId(),
                CustodyRecord.builder().custodian(UserSnapshot.builder().id(CUSTODIAN_ID_1).build()).build());
        assertThat(this.custodyRecordService.read(original.getId()).getCustodian().getId()).isEqualTo(CUSTODIAN_ID_1);
        verify(this.userFinder).read(CUSTODIAN_ID_1);
    }

    @Test
    void testPatchCustodianNotFoundLeavesRecordUnchanged() {
        CustodyRecord original = this.createRecord();
        CustodyRecord changes = CustodyRecord.builder()
                .custodian(UserSnapshot.builder().id(MISSING_CUSTODIAN_ID).build()).build();
        assertThatThrownBy(() -> this.custodyRecordService.patch(original.getId(), changes))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(MISSING_CUSTODIAN_ID.toString());
        assertThat(this.custodyRecordService.read(original.getId())).usingRecursiveComparison().isEqualTo(original);
    }

    @Test
    void testPatchWithoutCustodianDoesNotQueryUsers() {
        CustodyRecord original = this.createRecord();
        clearInvocations(this.userFinder);
        this.custodyRecordService.patch(original.getId(), CustodyRecord.builder().location("Patched").build());
        verify(this.userFinder, never()).read(any(UUID.class));
    }

    @Test
    void testPatchNotFound() {
        UUID id = UUID.randomUUID();
        CustodyRecord changes = CustodyRecord.builder().notes("Patched notes").build();
        assertThatThrownBy(() -> this.custodyRecordService.patch(id, changes))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testDelete() {
        CustodyRecord custodyRecord = this.createRecord();
        this.custodyRecordService.delete(custodyRecord.getId());
        assertThatThrownBy(() -> this.custodyRecordService.read(custodyRecord.getId()))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteReferencedRecord() {
        CustodyRecord custodyRecord = this.createRecord();
        this.evidenceRepository.saveAndFlush(EvidenceEntity.builder().id(UUID.randomUUID())
                .title("Evidence " + UUID.randomUUID()).evidenceType(EvidenceType.PHYSICAL)
                .status(EvidenceStatus.REGISTERED).confidential(false)
                .collectionDate(LocalDateTime.of(2025, 1, 1, 8, 0))
                .custodyRecords(List.of(new CustodyRecordEntity(custodyRecord))).build());
        assertThatThrownBy(() -> this.custodyRecordService.delete(custodyRecord.getId()))
                .isInstanceOf(ConflictException.class).hasMessageContaining(custodyRecord.getId().toString());
        assertThat(this.custodyRecordService.read(custodyRecord.getId()).getId()).isEqualTo(custodyRecord.getId());
    }

    @Test
    void testDeleteMissingRecord() {
        UUID id = UUID.randomUUID();
        this.custodyRecordService.delete(id);
        assertThatThrownBy(() -> this.custodyRecordService.read(id)).isInstanceOf(NotFoundException.class);
    }

    private CustodyRecord newRecord() {
        return CustodyRecord.builder().durationMinutes(30).action("IT action " + UUID.randomUUID())
                .location("Laboratory").notes("Original notes")
                .custodian(UserSnapshot.builder().id(CUSTODIAN_ID_0).build()).build();
    }

    private CustodyRecord createRecord() {
        CustodyRecord created = this.custodyRecordService.create(this.newRecord());
        return this.custodyRecordService.read(created.getId());
    }

    @Test
    void testPatchAllFields() {
        CustodyRecord original = this.createRecord();
        CustodyRecord changes = CustodyRecord.builder().durationMinutes(90).action("Patched action")
                .location("Patched location").notes("Patched notes")
                .custodian(UserSnapshot.builder().id(CUSTODIAN_ID_1).build()).build();
        this.custodyRecordService.patch(original.getId(), changes);
        CustodyRecord patched = this.custodyRecordService.read(original.getId());
        assertThat(patched.getId()).isEqualTo(original.getId());
        assertThat(patched.getRecordedAt()).isEqualTo(original.getRecordedAt());
        assertThat(patched.getDurationMinutes()).isEqualTo(90);
        assertThat(patched.getAction()).isEqualTo("Patched action");
        assertThat(patched.getLocation()).isEqualTo("Patched location");
        assertThat(patched.getNotes()).isEqualTo("Patched notes");
        assertThat(patched.getCustodian().getId()).isEqualTo(CUSTODIAN_ID_1);
    }
}