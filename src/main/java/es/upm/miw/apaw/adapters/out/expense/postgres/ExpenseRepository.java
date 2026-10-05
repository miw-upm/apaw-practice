package es.upm.miw.apaw.adapters.out.expense.postgres;

import es.upm.miw.apaw.domain.model.expense.SupplierExpenseReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, UUID> {
    boolean existsByReference(String reference);

    boolean existsBySupplierEntity_Id(UUID supplierId);

    @Query("""
            select new es.upm.miw.apaw.domain.model.expense.SupplierExpenseReport(
                supplier.companyName,
                count(expense),
                sum(expense.amount)
            )
            from ExpenseEntity expense
            join expense.supplierEntity supplier
            group by supplier.companyName
            order by sum(expense.amount) desc
            """)
    List<SupplierExpenseReport> findSupplierExpenseReport();
}