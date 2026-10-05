package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.expense.postgres.ExpenseEntity;
import es.upm.miw.apaw.adapters.out.expense.postgres.ExpenseRepository;
import es.upm.miw.apaw.adapters.out.expense.postgres.SupplierEntity;
import es.upm.miw.apaw.adapters.out.expense.postgres.SupplierRepository;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.expense.Expense;
import es.upm.miw.apaw.domain.model.expense.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(1)
@RequiredArgsConstructor
public class ExpenseSeederForDev implements ApplicationRunner {

    public static final String PREFIX = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff";

    public static final UUID SUPPLIER_ID_0 = UUID.fromString(PREFIX + "0000");
    public static final Supplier SUPPLIER_0 = Supplier.builder()
            .id(SUPPLIER_ID_0)
            .taxId("B12345678")
            .companyName("Office Supplies Corp")
            .address("Calle Mayor 1")
            .contactEmail("contact@officesupplies.es")
            .corporatePhone("912345678")
            .paymentTermsDays(30)
            .build();

    public static final UUID SUPPLIER_ID_1 = UUID.fromString(PREFIX + "0001");
    public static final Supplier SUPPLIER_1 = Supplier.builder()
            .id(SUPPLIER_ID_1)
            .taxId("A87654321")
            .companyName("Global Travel Agency")
            .address("Avenida Central 45")
            .contactEmail("info@globaltravel.com")
            .corporatePhone("918765432")
            .paymentTermsDays(15)
            .build();

    private static final String EXPENSE_PREFIX = "bbbbbbbb-cccc-dddd-eeee-ffffffff";

    public static final UUID EXPENSE_ID_0 = UUID.fromString(EXPENSE_PREFIX + "0000");
    public static final Expense EXPENSE_0 = Expense.builder()
            .id(EXPENSE_ID_0)
            .reference("EXP-SEED-001")
            .amount(new BigDecimal("150.00"))
            .description("Printer paper and ink")
            .expenseDate(LocalDate.of(2025, 1, 15))
            .category("Office")
            .isPaid(false)
            .supplier(SUPPLIER_0)
            .userSnapshot(user("0000", "600000100", "cliente0"))
            .build();

    public static final UUID EXPENSE_ID_1 = UUID.fromString(EXPENSE_PREFIX + "0001");
    public static final Expense EXPENSE_1 = Expense.builder()
            .id(EXPENSE_ID_1)
            .reference("EXP-SEED-002")
            .amount(new BigDecimal("300.00"))
            .description("Ergonomic chairs")
            .expenseDate(LocalDate.of(2025, 2, 10))
            .category("Office")
            .isPaid(true)
            .supplier(SUPPLIER_0)
            .userSnapshot(user("0001", "600000101", "cliente1"))
            .build();

    public static final UUID EXPENSE_ID_2 = UUID.fromString(EXPENSE_PREFIX + "0002");
    public static final Expense EXPENSE_2 = Expense.builder()
            .id(EXPENSE_ID_2)
            .reference("EXP-SEED-003")
            .amount(new BigDecimal("500.00"))
            .description("Flight to conference")
            .expenseDate(LocalDate.of(2025, 3, 20))
            .category("Travel")
            .isPaid(false)
            .supplier(SUPPLIER_1)
            .userSnapshot(user("0000", "600000100", "cliente0"))
            .build();

    private final SupplierRepository supplierRepository;
    private final ExpenseRepository expenseRepository;

    private static UserSnapshot user(String idSuffix, String mobile, String firstName) {
        return UserSnapshot.builder()
                .id(UUID.fromString(PREFIX + idSuffix))
                .mobile(mobile)
                .firstName(firstName)
                .build();
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load from JAVA -----------");
        this.seedSuppliers();
        this.seedExpenses();
    }

    private void seedSuppliers() {
        List<SupplierEntity> suppliers = List.of(SUPPLIER_0, SUPPLIER_1).stream()
                .filter(supplier -> !this.supplierRepository.existsById(supplier.getId()))
                .map(SupplierEntity::new)
                .toList();
        this.supplierRepository.saveAll(suppliers);
        log.warn("        ------- suppliers: {} added", suppliers.size());
    }

    private void seedExpenses() {
        List<ExpenseEntity> expenses = List.of(EXPENSE_0, EXPENSE_1, EXPENSE_2).stream()
                .filter(expense -> !this.expenseRepository.existsById(expense.getId()))
                .map(this::toEntity)
                .toList();
        this.expenseRepository.saveAll(expenses);
        log.warn("        ------- expenses: {} added", expenses.size());
    }

    private ExpenseEntity toEntity(Expense expense) {
        ExpenseEntity entity = new ExpenseEntity(expense);
        entity.setSupplierEntity(this.supplierRepository.getReferenceById(expense.getSupplier().getId()));
        return entity;
    }
}