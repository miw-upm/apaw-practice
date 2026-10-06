package es.upm.miw.apaw.domain.services.secondlawchance;

import es.upm.miw.apaw.adapters.out.secondlawchance.postgres.DebtEntity;
import es.upm.miw.apaw.adapters.out.secondlawchance.postgres.ExonerationCaseEntity;
import es.upm.miw.apaw.adapters.out.secondlawchance.postgres.ExonerationCaseRepository;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.secondlawchance.CreditorType;
import es.upm.miw.apaw.domain.model.secondlawchance.Debt;
import es.upm.miw.apaw.domain.model.secondlawchance.DebtPatch;
import es.upm.miw.apaw.domain.model.secondlawchance.SharedDebtReport;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.SecondLawChanceSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class DebtServiceIT {
    @Autowired
    private DebtService debtService;
    @Autowired
    private ExonerationCaseRepository exonerationCaseRepository;
    @MockitoBean
    private UserFinder userFinder;

    @Test
    void testReadSeeder() {
        assertThat(this.debtService.read(DEBT_ID_0)).usingRecursiveComparison().isEqualTo(DEBT_0);
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.debtService.read(id))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testFindAllAllowsAdditionalDebts() {
        Debt extra = this.createDebt();
        List<Debt> debts = this.debtService.findAll();
        assertThat(debts).extracting(Debt::getId).contains(
                DEBT_ID_0, DEBT_ID_1, DEBT_ID_2, DEBT_ID_3, DEBT_ID_4, DEBT_ID_5, extra.getId());
        assertThat(debts).extracting(Debt::getId).containsSubsequence(
                DEBT_ID_2, DEBT_ID_3, DEBT_ID_0, DEBT_ID_1, DEBT_ID_4, DEBT_ID_5);
        assertThat(this.debtService.findAll()).extracting(Debt::getId)
                .containsExactlyElementsOf(debts.stream().map(Debt::getId).toList());
    }

    @Test
    void testCreate() {
        Debt debt = this.createDebt();
        Debt stored = this.debtService.read(debt.getId());
        assertThat(stored).usingRecursiveComparison().isEqualTo(debt);
        assertThat(stored.getType()).isEqualTo(CreditorType.PUBLIC);
        assertThat(stored.getGuarantee()).isTrue();
    }

    @Test
    void testCreateDefaults() {
        Debt debt = this.debtService.create(this.buildDebt().type(null).guarantee(null).build());
        Debt stored = this.debtService.read(debt.getId());
        assertThat(stored.getId()).isNotNull();
        assertThat(stored.getType()).isEqualTo(CreditorType.PRIVATE);
        assertThat(stored.getGuarantee()).isFalse();
    }

    @Test
    void testCreateDuplicateContractNumber() {
        Debt debt = this.buildDebt().contractNumber(DEBT_0.getContractNumber()).build();
        assertThatThrownBy(() -> this.debtService.create(debt))
                .isInstanceOf(ConflictException.class).hasMessageContaining(DEBT_0.getContractNumber());
    }

    @Test
    void testUpdateReplacesAllFields() {
        Debt original = this.createDebt();
        Debt replacement = this.buildDebt().issueDate(LocalDate.of(2023, 5, 5))
                .creditorName("Updated creditor").amount(new BigDecimal("250.50"))
                .type(null).guarantee(null).build();
        this.debtService.update(original.getId(), replacement);
        Debt updated = this.debtService.read(original.getId());
        assertThat(updated.getId()).isEqualTo(original.getId());
        assertThat(updated.getContractNumber()).isEqualTo(replacement.getContractNumber());
        assertThat(updated.getIssueDate()).isEqualTo(LocalDate.of(2023, 5, 5));
        assertThat(updated.getCreditorName()).isEqualTo("Updated creditor");
        assertThat(updated.getAmount()).isEqualByComparingTo("250.50");
        assertThat(updated.getType()).isEqualTo(CreditorType.PRIVATE);
        assertThat(updated.getGuarantee()).isFalse();
    }

    @Test
    void testUpdateSameContractNumber() {
        Debt debt = this.createDebt();
        debt.setCreditorName("Same number creditor");
        this.debtService.update(debt.getId(), debt);
        assertThat(this.debtService.read(debt.getId()).getCreditorName()).isEqualTo("Same number creditor");
    }

    @Test
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();
        Debt debt = this.buildDebt().build();
        assertThatThrownBy(() -> this.debtService.update(id, debt))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testUpdateDuplicateContractNumberLeavesDebtUnchanged() {
        Debt debt = this.createDebt();
        Debt replacement = this.buildDebt().contractNumber(DEBT_0.getContractNumber()).build();
        assertThatThrownBy(() -> this.debtService.update(debt.getId(), replacement))
                .isInstanceOf(ConflictException.class).hasMessageContaining(DEBT_0.getContractNumber());
        assertThat(this.debtService.read(debt.getId()).getContractNumber()).isEqualTo(debt.getContractNumber());
    }

    @Test
    void testPatchChangesOnlyPresentFields() {
        Debt original = this.createDebt();
        this.debtService.patch(original.getId(),
                new DebtPatch(null, null, null, new BigDecimal("999.99"), null, false));
        Debt patched = this.debtService.read(original.getId());
        assertThat(patched.getAmount()).isEqualByComparingTo("999.99");
        assertThat(patched.getGuarantee()).isFalse();
        assertThat(patched.getContractNumber()).isEqualTo(original.getContractNumber());
        assertThat(patched.getIssueDate()).isEqualTo(original.getIssueDate());
        assertThat(patched.getCreditorName()).isEqualTo(original.getCreditorName());
        assertThat(patched.getType()).isEqualTo(original.getType());
    }

    @Test
    void testPatchEmptyChangesNothing() {
        Debt original = this.createDebt();
        this.debtService.patch(original.getId(), new DebtPatch(null, null, null, null, null, null));
        assertThat(this.debtService.read(original.getId())).usingRecursiveComparison().isEqualTo(original);
    }

    @Test
    void testPatchSameContractNumber() {
        Debt debt = this.createDebt();
        this.debtService.patch(debt.getId(),
                new DebtPatch(debt.getContractNumber(), null, "Patched creditor", null, null, null));
        assertThat(this.debtService.read(debt.getId()).getCreditorName()).isEqualTo("Patched creditor");
    }

    @Test
    void testPatchNotFound() {
        UUID id = UUID.randomUUID();
        DebtPatch patch = new DebtPatch(null, null, "Missing", null, null, null);
        assertThatThrownBy(() -> this.debtService.patch(id, patch))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testPatchDuplicateContractNumberLeavesDebtUnchanged() {
        Debt debt = this.createDebt();
        DebtPatch patch = new DebtPatch(DEBT_0.getContractNumber(), null, "Patched creditor", null, null, null);
        assertThatThrownBy(() -> this.debtService.patch(debt.getId(), patch))
                .isInstanceOf(ConflictException.class).hasMessageContaining(DEBT_0.getContractNumber());
        assertThat(this.debtService.read(debt.getId())).usingRecursiveComparison().isEqualTo(debt);
    }

    @Test
    void testDelete() {
        Debt debt = this.createDebt();
        this.debtService.delete(debt.getId());
        assertThatThrownBy(() -> this.debtService.read(debt.getId())).isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteMissingDebt() {
        UUID id = UUID.randomUUID();
        this.debtService.delete(id);
        assertThatThrownBy(() -> this.debtService.read(id)).isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteReferencedDebt() {
        Debt debt = this.createDebt();
        ExonerationCaseEntity exonerationCase = ExonerationCaseEntity.builder().id(UUID.randomUUID())
                .caseNumber("IT-CASE-" + UUID.randomUUID()).filingDate(LocalDate.of(2025, 1, 1))
                .debts(List.of(new DebtEntity(debt))).userId(UUID.randomUUID()).build();
        this.exonerationCaseRepository.saveAndFlush(exonerationCase);
        assertThatThrownBy(() -> this.debtService.delete(debt.getId()))
                .isInstanceOf(ConflictException.class).hasMessageContaining(debt.getId().toString());
        assertThat(this.debtService.read(debt.getId()).getId()).isEqualTo(debt.getId());
        assertThat(this.exonerationCaseRepository.existsByDebtsId(debt.getId())).isTrue();
    }

    @Test
    void testFindSharedReport() {
        UUID firstDebtor = UUID.randomUUID();
        UUID secondDebtor = UUID.randomUUID();
        Debt debt = this.createDebt();
        this.saveCase(firstDebtor, debt);
        this.saveCase(secondDebtor, debt);
        when(this.userFinder.findByIds(anySet()))
                .thenAnswer(invocation -> this.snapshots(invocation.getArgument(0)));

        List<SharedDebtReport> report = this.debtService.findSharedReport();

        assertThat(report).filteredOn(item -> item.getDebtId().equals(debt.getId()))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getContractNumber()).isEqualTo(debt.getContractNumber());
                    assertThat(item.getCaseCount()).isEqualTo(2);
                    assertThat(item.getDebtorCount()).isEqualTo(2);
                    assertThat(item.getDebtors()).extracting(UserSnapshot::getId)
                            .containsExactlyInAnyOrder(firstDebtor, secondDebtor);
                });
        assertThat(report).allSatisfy(item -> assertThat(item.getDebtors()).hasSameSizeAs(item.getDebtorIds()));
        verify(this.userFinder, times(1)).findByIds(anySet());
    }

    @Test
    void testFindSharedReportUserNotFound() {
        Debt debt = this.createDebt();
        this.saveCase(UUID.randomUUID(), debt);
        this.saveCase(UUID.randomUUID(), debt);
        when(this.userFinder.findByIds(anySet())).thenReturn(List.of());

        assertThatThrownBy(() -> this.debtService.findSharedReport())
                .isInstanceOf(NotFoundException.class).hasMessageContaining("User id not found");
    }

    private void saveCase(UUID userId, Debt debt) {
        this.exonerationCaseRepository.saveAndFlush(ExonerationCaseEntity.builder().id(UUID.randomUUID())
                .caseNumber("IT-CASE-" + UUID.randomUUID()).filingDate(LocalDate.of(2025, 1, 1))
                .debts(List.of(new DebtEntity(debt))).userId(userId).build());
    }

    private List<UserSnapshot> snapshots(Set<UUID> ids) {
        return ids.stream()
                .map(id -> UserSnapshot.builder().id(id).mobile("600000000").firstName("user").build())
                .toList();
    }

    private Debt createDebt() {
        return this.debtService.create(this.buildDebt().build());
    }

    private Debt.DebtBuilder buildDebt() {
        return Debt.builder().contractNumber("IT-" + UUID.randomUUID()).issueDate(LocalDate.of(2024, 3, 1))
                .creditorName("IT creditor").amount(new BigDecimal("100.00"))
                .type(CreditorType.PUBLIC).guarantee(true);
    }
}
