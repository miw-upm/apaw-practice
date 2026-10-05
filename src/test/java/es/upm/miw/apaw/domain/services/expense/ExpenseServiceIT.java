package es.upm.miw.apaw.domain.services.expense;

import es.upm.miw.apaw.adapters.out.expense.postgres.ExpenseEntity;
import es.upm.miw.apaw.adapters.out.expense.postgres.ExpenseRepository;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.expense.CreationExpense;
import es.upm.miw.apaw.domain.model.expense.Expense;
import es.upm.miw.apaw.domain.model.expense.ExpenseFindCriteria;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.SupplierSeederForDev.SUPPLIER_1_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class ExpenseServiceIT {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenseRepository expenseRepository;

    @MockitoBean
    private UserFinder userFinder;

    @Test
    @Transactional
    void testCreate() {
        UserSnapshot user = UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .mobile("600000100")
                .firstName("cliente0")
                .build();

        CreationExpense creation = CreationExpense.builder()
                .reference("EXP-" + UUID.randomUUID())
                .amount(new BigDecimal("150.50"))
                .description("Office supplies purchase")
                .category("Supplies")
                .supplierId(SUPPLIER_1_ID)
                .applicantId(user.getId())
                .build();

        when(this.userFinder.read(user.getId())).thenReturn(user);

        Expense expense = this.expenseService.create(creation);

        assertThat(expense.getId()).isNotNull();
        assertThat(expense.getReference()).isEqualTo(creation.getReference());
        assertThat(expense.getExpenseDate()).isEqualTo(LocalDate.now());
        assertThat(expense.getIsPaid()).isFalse();
        assertThat(expense.getSupplier().getId()).isEqualTo(SUPPLIER_1_ID);
        assertThat(expense.getUserSnapshot()).isEqualTo(user);

        ExpenseEntity entity = this.expenseRepository.findById(expense.getId()).orElseThrow();
        assertThat(entity.getReference()).isEqualTo(creation.getReference());
        assertThat(entity.getSupplierEntity().getId()).isEqualTo(SUPPLIER_1_ID);
        assertThat(entity.getUserId()).isEqualTo(user.getId());
    }

    @Test
    @Transactional
    void testFindByUserMobile() {
        UserSnapshot firstUser = UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .mobile("600000100")
                .firstName("cliente0")
                .build();
        UserSnapshot secondUser = UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                .mobile("600000101")
                .firstName("cliente1")
                .build();

        when(this.userFinder.read(firstUser.getId())).thenReturn(firstUser);
        when(this.userFinder.read(secondUser.getId())).thenReturn(secondUser);

        Expense first = this.expenseService.create(this.creation(firstUser.getId()));
        Expense second = this.expenseService.create(this.creation(secondUser.getId()));

        when(this.userFinder.findByIds(Set.of(firstUser.getId(), secondUser.getId())))
                .thenReturn(List.of(firstUser, secondUser));

        List<Expense> expenses = this.expenseService.find(
                ExpenseFindCriteria.builder().unpaid(true).userMobile(firstUser.getMobile()).build());

        assertThat(expenses).extracting(Expense::getId)
                .contains(first.getId()).doesNotContain(second.getId());
        assertThat(expenses).filteredOn(expense -> expense.getId().equals(first.getId()))
                .singleElement().extracting(Expense::getUserSnapshot).isEqualTo(firstUser);
    }

    @Test
    @Transactional
    void testCreateDuplicateReference() {
        UserSnapshot user = UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .build();

        CreationExpense creation = CreationExpense.builder()
                .reference("EXP-DUP-REF")
                .amount(BigDecimal.TEN)
                .description("Duplicate test")
                .supplierId(SUPPLIER_1_ID)
                .applicantId(user.getId())
                .build();

        when(this.userFinder.read(user.getId())).thenReturn(user);

        this.expenseService.create(creation);

        assertThatThrownBy(() -> this.expenseService.create(creation))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("EXP-DUP-REF");
    }

    @Test
    @Transactional
    void testCreateNotFoundSupplier() {
        UserSnapshot user = UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .build();

        UUID missingSupplierId = UUID.randomUUID();
        CreationExpense creation = CreationExpense.builder()
                .reference("EXP-" + UUID.randomUUID())
                .amount(BigDecimal.TEN)
                .description("Missing supplier test")
                .supplierId(missingSupplierId)
                .applicantId(user.getId())
                .build();

        when(this.userFinder.read(user.getId())).thenReturn(user);

        assertThatThrownBy(() -> this.expenseService.create(creation))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(missingSupplierId.toString());
    }

    private CreationExpense creation(UUID userId) {
        return CreationExpense.builder()
                .reference("EXP-" + UUID.randomUUID())
                .amount(BigDecimal.TEN)
                .description("Find Criteria test")
                .supplierId(SUPPLIER_1_ID)
                .applicantId(userId)
                .build();
    }
}