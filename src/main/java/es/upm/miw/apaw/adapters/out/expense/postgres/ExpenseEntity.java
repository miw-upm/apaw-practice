package es.upm.miw.apaw.adapters.out.expense.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.expense.Expense;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ExpenseEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String reference;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String description;

    private LocalDate expenseDate;

    private String category;

    private Boolean isPaid;

    @ManyToOne
    private SupplierEntity supplierEntity;

    @Column(nullable = false)
    private UUID userId;

    public ExpenseEntity(Expense expense) {
        BeanUtils.copyProperties(expense, this, "supplierEntity", "userSnapshot");
        if (expense.getSupplier() != null) {
            this.supplierEntity = new SupplierEntity(expense.getSupplier());
        }
        if (expense.getUserSnapshot() != null) {
            this.userId = expense.getUserSnapshot().getId();
        }
    }

    public Expense toDomain() {
        Expense expense = new Expense();
        BeanUtils.copyProperties(this, expense, "supplierEntity", "userId");
        if (this.supplierEntity != null) {
            expense.setSupplier(this.supplierEntity.toDomain());
        }
        expense.setUserSnapshot(UserSnapshot.builder().id(this.userId).build());
        return expense;
    }
}