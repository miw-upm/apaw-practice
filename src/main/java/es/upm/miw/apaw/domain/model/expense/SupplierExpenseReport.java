package es.upm.miw.apaw.domain.model.expense;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierExpenseReport {
    private String companyName;
    private long totalExpenses;
    private BigDecimal totalAmount;
}