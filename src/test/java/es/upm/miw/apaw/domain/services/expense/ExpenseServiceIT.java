package es.upm.miw.apaw.domain.services.expense;

import es.upm.miw.apaw.adapters.in.expense.ExpenseCreationDto;
import es.upm.miw.apaw.config.seeders.SupplierSeederForDev;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.expense.Expense;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ExpenseServiceIT {

    @Autowired
    private ExpenseService expenseService;

    @MockitoBean
    private UserFinder userFinder;

    private final UUID userId = UUID.fromString("11111111-1111-1111-1111-111111111111");

    @BeforeEach
    void setUp() {
        UserSnapshot userSnapshot = UserSnapshot.builder()
                .id(this.userId)
                .firstName("TestUser")
                .familyName("APAW")
                .email("testuser@apaw.es")
                .build();

        Mockito.when(this.userFinder.read(this.userId))
                .thenReturn(userSnapshot);
    }

    @Test
    void testCreateSuccess() {
        ExpenseCreationDto dto = ExpenseCreationDto.builder()
                .reference("EXP-2026-IT01")
                .amount(new BigDecimal("150.50"))
                .description("Office chairs purchase")
                .category("Supplies")
                .supplierId(SupplierSeederForDev.SUPPLIER_1_ID)
                .applicantId(this.userId)
                .build();

        Expense expense = this.expenseService.create(dto);

        assertNotNull(expense.getId());
        assertEquals("EXP-2026-IT01", expense.getReference());
        assertNotNull(expense.getExpenseDate());
        assertEquals(false, expense.getIsPaid());
        assertNotNull(expense.getSupplier());
        assertNotNull(expense.getUserSnapshot());
        assertEquals(this.userId, expense.getUserSnapshot().getId());
    }

    @Test
    void testCreateNotFoundSupplier() {
        ExpenseCreationDto dto = ExpenseCreationDto.builder()
                .reference("EXP-2026-IT02")
                .amount(new BigDecimal("50.00"))
                .description("Unknown supplier test")
                .supplierId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .applicantId(this.userId)
                .build();

        assertThrows(NotFoundException.class, () -> this.expenseService.create(dto));
    }

    @Test
    void testCreateNotFoundUser() {
        UUID unknownUserId = UUID.fromString("99999999-9999-9999-9999-999999999999");
        Mockito.when(this.userFinder.read(unknownUserId))
                .thenThrow(new NotFoundException("User not found: " + unknownUserId));

        ExpenseCreationDto dto = ExpenseCreationDto.builder()
                .reference("EXP-2026-IT03")
                .amount(new BigDecimal("50.00"))
                .description("Unknown user test")
                .supplierId(SupplierSeederForDev.SUPPLIER_1_ID)
                .applicantId(unknownUserId)
                .build();

        assertThrows(NotFoundException.class, () -> this.expenseService.create(dto));
    }

    @Test
    void testCreateConflictReference() {
        ExpenseCreationDto dto = ExpenseCreationDto.builder()
                .reference("EXP-2026-DUP")
                .amount(new BigDecimal("100.00"))
                .description("Duplicate reference test")
                .supplierId(SupplierSeederForDev.SUPPLIER_1_ID)
                .applicantId(this.userId)
                .build();

        this.expenseService.create(dto);

        assertThrows(ConflictException.class, () -> this.expenseService.create(dto));
    }
}