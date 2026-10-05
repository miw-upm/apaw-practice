package es.upm.miw.apaw.adapters.out.expense.postgres;

import es.upm.miw.apaw.config.seeders.SupplierSeederForDev;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.expense.Expense;
import es.upm.miw.apaw.domain.model.expense.Supplier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ExpenseAdapterIT {

    @Autowired
    private ExpenseAdapter expenseAdapter;

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    void testCreateAndExistsByReference() {
        SupplierEntity supplierEntity = this.supplierRepository.findById(SupplierSeederForDev.SUPPLIER_1_ID)
                .orElseThrow();

        Supplier supplierDomain = new Supplier();
        BeanUtils.copyProperties(supplierEntity, supplierDomain);

        UserSnapshot userSnapshot = UserSnapshot.builder()
                .id(UUID.randomUUID())
                .firstName("AdapterUser")
                .familyName("APAW")
                .email("adapteruser@apaw.es")
                .build();

        Expense expense = Expense.builder()
                .id(UUID.randomUUID())
                .reference("EXP-ADAPTER-001")
                .amount(new BigDecimal("99.99"))
                .description("Adapter Test Expense")
                .expenseDate(LocalDate.now())
                .category("Testing")
                .isPaid(false)
                .supplier(supplierDomain)
                .userSnapshot(userSnapshot)
                .build();

        Expense created = this.expenseAdapter.create(expense);

        assertThat(created).isNotNull();
        assertThat(created.getReference()).isEqualTo("EXP-ADAPTER-001");
        assertThat(created.getAmount()).isEqualTo(new BigDecimal("99.99"));
        assertThat(created.getSupplier()).isNotNull();
        assertThat(created.getSupplier().getId()).isEqualTo(SupplierSeederForDev.SUPPLIER_1_ID);

        assertThat(this.expenseAdapter.existsByReference("EXP-ADAPTER-001")).isTrue();
        assertThat(this.expenseAdapter.isSupplierInUse(SupplierSeederForDev.SUPPLIER_1_ID)).isTrue();
    }

    @Test
    void testExistsByReferenceNotFound() {
        assertThat(this.expenseAdapter.existsByReference("NON-EXISTENT-REF")).isFalse();
    }
}