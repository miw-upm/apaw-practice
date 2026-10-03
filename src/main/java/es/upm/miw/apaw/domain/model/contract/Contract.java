package es.upm.miw.apaw.domain.model.contract;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Contract {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String title;

    private ContractType type;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal amount;

    @NotNull
    private Boolean automaticRenewal;

    @NotNull
    private LocalDateTime createdAt;

    private List<Clause> clauses;

    private UserSnapshot userSnapshot;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();

        if (this.type == null) {
            this.type = ContractType.OTHER;
        }

        if (this.automaticRenewal == null) {
            this.automaticRenewal = false;
        }
    }

    public boolean isActive() {
        return this.endDate == null || !this.endDate.isBefore(LocalDate.now());
    }

    public Contract ofSummary() {
        return Contract.builder()
                .id(this.id)
                .title(this.title)
                .type(this.type)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .amount(this.amount)
                .automaticRenewal(this.automaticRenewal)
                .createdAt(this.createdAt)
                .userSnapshot(UserSnapshot.builder()
                        .id(this.userSnapshot.getId())
                        .mobile(this.userSnapshot.getMobile())
                        .firstName(this.userSnapshot.getFirstName())
                        .build())
                .build();
    }
}