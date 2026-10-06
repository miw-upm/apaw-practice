package es.upm.miw.apaw.adapters.out.expense.postgres;

import es.upm.miw.apaw.config.seeders.ExpenseSeederForDev;
import es.upm.miw.apaw.domain.model.expense.SupplierExpenseReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ExpenseRepositoryIT {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    void testFindSupplierExpenseReport() {
        SupplierEntity supplier = this.supplierRepository.findById(ExpenseSeederForDev.SUPPLIER_ID_0).orElseThrow();

        ExpenseEntity expense1 = ExpenseEntity.builder()
                .id(UUID.randomUUID())
                .reference("EXP-REP-01")
                .amount(new BigDecimal("200.00"))
                .description("Report Test 1")
                .expenseDate(LocalDate.now())
                .supplierEntity(supplier)
                .userId(UUID.randomUUID())
                .build();

        ExpenseEntity expense2 = ExpenseEntity.builder()
                .id(UUID.randomUUID())
                .reference("EXP-REP-02")
                .amount(new BigDecimal("300.00"))
                .description("Report Test 2")
                .expenseDate(LocalDate.now())
                .supplierEntity(supplier)
                .userId(UUID.randomUUID())
                .build();

        this.expenseRepository.saveAll(List.of(expense1, expense2));

        List<SupplierExpenseReport> report = this.expenseRepository.findSupplierExpenseReport();

        assertThat(report).isNotNull();
        assertThat(report).extracting(SupplierExpenseReport::getTotalAmount)
                .isSortedAccordingTo(Comparator.nullsLast(Comparator.reverseOrder()));

        assertThat(report).filteredOn(item -> item.getCompanyName().equals(supplier.getCompanyName()))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getTotalExpenses()).isGreaterThanOrEqualTo(2);
                    assertThat(item.getTotalAmount()).isGreaterThanOrEqualTo(new BigDecimal("500.00"));
                });
    }
}