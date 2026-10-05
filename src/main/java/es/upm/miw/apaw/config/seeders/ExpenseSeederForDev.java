package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.expense.postgres.ExpenseEntity;
import es.upm.miw.apaw.adapters.out.expense.postgres.ExpenseRepository;
import es.upm.miw.apaw.adapters.out.expense.postgres.SupplierRepository;
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
@Order(2)
@RequiredArgsConstructor
public class ExpenseSeederForDev implements ApplicationRunner {

    public static final String PREFIX = "bbbbbbbb-cccc-dddd-eeee-ffffffff";
    public static final UUID EXPENSE_1_ID = UUID.fromString(PREFIX + "0001");
    public static final UUID EXPENSE_2_ID = UUID.fromString(PREFIX + "0002");

    public static final UUID USER_1_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000");

    private final ExpenseRepository expenseRepository;
    private final SupplierRepository supplierRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (this.expenseRepository.count() == 0) {
            this.supplierRepository.findById(SupplierSeederForDev.SUPPLIER_1_ID).ifPresent(supplier -> {
                ExpenseEntity expense1 = ExpenseEntity.builder()
                        .id(EXPENSE_1_ID)
                        .reference("EXP-SEED-001")
                        .amount(new BigDecimal("100.00"))
                        .description("Seeded office supplies")
                        .category("Office")
                        .expenseDate(LocalDate.now())
                        .isPaid(false)
                        .supplierEntity(supplier)
                        .userId(USER_1_ID)
                        .build();

                ExpenseEntity expense2 = ExpenseEntity.builder()
                        .id(EXPENSE_2_ID)
                        .reference("EXP-SEED-002")
                        .amount(new BigDecimal("250.00"))
                        .description("Seeded travel expense")
                        .category("Travel")
                        .expenseDate(LocalDate.now())
                        .isPaid(true)
                        .supplierEntity(supplier)
                        .userId(USER_1_ID)
                        .build();

                this.expenseRepository.saveAll(List.of(expense1, expense2));
                log.warn("        ------- expenses seeded: 2");
            });
        }
    }
}