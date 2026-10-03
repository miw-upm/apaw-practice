package es.upm.miw.apaw.domain.model.secondlawchance;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Debt {
    @EqualsAndHashCode.Include
    private UUID id;

    @NotNull
    private LocalDate issueDate;

    @NotBlank
    private String creditorName;

    @NotNull
    private BigDecimal amount;

    private CreditorType type;

    private Boolean guarantee;

    public void doDefault() {
        this.id = UUID.randomUUID();
        if (this.type == null) {
            this.type = CreditorType.PRIVATE;
        }
        if (this.guarantee == null) {
            this.guarantee = false;
        }
    }
}
