package es.upm.miw.apaw.domain.model.expense;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Expense {
    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String reference;

    @NotNull
    private BigDecimal amount;

    @NotBlank
    private String description;

    private LocalDate expenseDate;
    private String category;
    private Boolean isPaid;

    private Supplier supplier;
    private UserSnapshot userSnapshot;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.expenseDate = LocalDate.now();
        if (this.isPaid == null) {
            this.isPaid = false;
        }
    }

    public Expense ofSummary() {
        return Expense.builder()
                .id(this.id)
                .reference(this.reference)
                .amount(this.amount)
                .description(this.description)
                .expenseDate(this.expenseDate)
                .category(this.category)
                .isPaid(this.isPaid)
                .userSnapshot(this.userSnapshot != null ? UserSnapshot.builder()
                        .id(this.userSnapshot.getId())
                        .firstName(this.userSnapshot.getFirstName())
                        .build() : null)
                .build();
    }
}